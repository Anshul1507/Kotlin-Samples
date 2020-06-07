/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.background

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.work.WorkInfo
import com.bumptech.glide.Glide
import com.example.background.databinding.ActivityBlurBinding

class BlurActivity : AppCompatActivity() {

    private lateinit var viewModel: BlurViewModel
    private lateinit var binding: ActivityBlurBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBlurBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the ViewModel
        viewModel = ViewModelProvider (this).get(BlurViewModel::class.java)
        //Show work status
        viewModel.outputWorkInfos.observe(this,workInfosObserver())
        // Image uri should be stored in the ViewModel; put it there then display
        val imageUriExtra = intent.getStringExtra(KEY_IMAGE_URI)
        viewModel.setImageUri(imageUriExtra)
        viewModel.imageUri?.let { imageUri ->
            Glide.with(this).load(imageUri).into(binding.imageView)
        }

        binding.goButton.setOnClickListener { viewModel.applyBlur(blurLevel) }

        binding.cancelButton.setOnClickListener{viewModel.cancelWork()}

        binding.seeFileButton.setOnClickListener{
            viewModel.outputUri?.let { currentUri ->
                val actionView = Intent(Intent.ACTION_VIEW,currentUri)
                actionView.resolveActivity(packageManager)?.run {
                    startActivity(actionView)
                }
            }
        }
    }

    private fun workInfosObserver(): Observer<List<WorkInfo>> {
        return Observer { listOfWorkInfo ->
            if(listOfWorkInfo.isNullOrEmpty()){
                return@Observer
            }
            val workInfo = listOfWorkInfo[0]
            if(workInfo.state.isFinished){
                showWorkFinished()

                val outputImageUri = workInfo.outputData.getString(KEY_IMAGE_URI)
                if(!outputImageUri.isNullOrEmpty()){
                    viewModel.setOutputUri(outputImageUri)
                    binding.seeFileButton.visibility = View.VISIBLE
                }
            }else{
                showWorkInProgress()
            }
        }
    }

    /**
     * Shows and hides views for when the Activity is processing an image
     */
    private fun showWorkInProgress() {
        with(binding) {
            progressBar.visibility = View.VISIBLE
            cancelButton.visibility = View.VISIBLE
            goButton.visibility = View.GONE
            seeFileButton.visibility = View.GONE
        }
    }

    /**
     * Shows and hides views for when the Activity is done processing an image
     */
    private fun showWorkFinished() {
        with(binding) {
            progressBar.visibility = View.GONE
            cancelButton.visibility = View.GONE
            goButton.visibility = View.VISIBLE
        }
    }

    private val blurLevel: Int
        get() =
            when (binding.radioBlurGroup.checkedRadioButtonId) {
                R.id.radio_blur_lv_1 -> 1
                R.id.radio_blur_lv_2 -> 2
                R.id.radio_blur_lv_3 -> 3
                else -> 1
            }
}
