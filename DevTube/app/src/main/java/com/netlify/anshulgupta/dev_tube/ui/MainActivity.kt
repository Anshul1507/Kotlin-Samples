package com.netlify.anshulgupta.dev_tube.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import com.netlify.anshulgupta.dev_tube.R
import timber.log.Timber

const val PREFS_NAME = "theme_prefs"
const val THEME_LIGHT = 0
const val THEME_DARK = 1

class MainActivity : AppCompatActivity() {

    private val sharedPrefs by lazy {
        getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu,menu)
        return true
    }

    private fun getSavedTheme() = sharedPrefs.getInt("prefs.theme",-1)

    private fun saveTheme(theme: Int) = sharedPrefs.edit().putInt("prefs.theme",theme).apply()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.day_night_mode -> {
                if(getSavedTheme() == THEME_DARK){
                    //Current DARK -> LIGHT
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    saveTheme(THEME_LIGHT)
                }else{
                    // LIGHT -> DARK
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    saveTheme(THEME_DARK)
                }
            }
        }
        return true
    }
}
