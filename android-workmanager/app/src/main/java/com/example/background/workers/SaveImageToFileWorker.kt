package com.example.background.workers

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.background.KEY_IMAGE_URI
import timber.log.Timber
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

@Suppress("DEPRECATION")
class SaveImageToFileWorker (ctx: Context, params: WorkerParameters) : Worker(ctx,params) {

    private val title = "Blurred Image"
    private val dateFormatter = SimpleDateFormat(
            "yyyy.MM.dd 'at' HH:mm::ss z",
            Locale.getDefault()
    )

    override fun doWork(): Result {
        makeStatusNotification("Saving image",applicationContext)
        sleep()

        val resolver = applicationContext.contentResolver

        return try{
            val resourceUri = inputData.getString(KEY_IMAGE_URI)
            val bitmap = BitmapFactory.decodeStream(
                    resolver.openInputStream(Uri.parse(resourceUri))
            )
            val imageUrl = MediaStore.Images.Media.insertImage(
                    resolver,bitmap,title,dateFormatter.format(Date())
            )
            if(!imageUrl.isNullOrEmpty()){
                val output = workDataOf(KEY_IMAGE_URI to imageUrl)
                Result.success(output)
            }else{
                Timber.e("Writing to MediaStore failed")
                Result.failure()
            }

        }catch (exception: Exception){
            Timber.e(exception)
            Result.failure()
        }

    }

}