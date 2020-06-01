package com.netlify.anshulgupta.dev_tube

import android.app.Application
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import androidx.work.*
import com.netlify.anshulgupta.dev_tube.work.RefreshDataWork
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * Override application to setup background work via WorkManager
 */
class MainApplication : Application() {

    private val applicationScope = CoroutineScope(Dispatchers.Default)
    /**
     * onCreate is called before the first screen is shown to the user.
     *
     * Use it to setup any background tasks, running expensive setup operations in a background
     * thread to avoid delaying app start.
     */
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.getDefaultNightMode())
        Timber.plant(Timber.DebugTree())
        delayedInit()
    }
    private fun delayedInit(){
        applicationScope.launch {
            setupRecurringWork()
        }
    }
    private fun setupRecurringWork(){

        //Constraints when to be work manager runs in day once.
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresBatteryNotLow(true)
            .setRequiresCharging(true)
            .apply {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    setRequiresDeviceIdle(true)
                }
            }.build()

        val repeatingRequest = PeriodicWorkRequestBuilder<RefreshDataWork>(1,TimeUnit.DAYS)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance().enqueueUniquePeriodicWork(
            RefreshDataWork.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest
        )

    }
}