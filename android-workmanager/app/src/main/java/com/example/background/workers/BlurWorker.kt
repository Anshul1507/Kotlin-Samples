package com.example.background.workers

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.text.TextUtils
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.background.KEY_IMAGE_URI
import com.example.background.R
import timber.log.Timber
import java.lang.IllegalArgumentException

class BlurWorker(ctx: Context,params: WorkerParameters) : Worker(ctx,params) {
    override fun doWork(): Result {
        val appContext = applicationContext
        val resourceUri = inputData.getString(KEY_IMAGE_URI)

        makeStatusNotification("Blurring Image",appContext)
        sleep()

        return try {

            if(TextUtils.isEmpty(resourceUri)){
                Timber.e("Invalid input uri")
                throw IllegalArgumentException("Invalid input uri")
            }
            val resolver = appContext.contentResolver
            val picture = BitmapFactory.decodeStream(
                    resolver.openInputStream(Uri.parse(resourceUri))
            )

            val output = blurBitmap(picture,appContext)

            val outputUri = writeBitmapToFile(appContext,output)

            val outputData = workDataOf(KEY_IMAGE_URI to outputUri.toString())
            makeStatusNotification("Output is $outputUri",appContext)
            Result.success(outputData)
        }catch (throwable: Throwable){
            Timber.e(throwable,"Error applying blur")
            Result.failure()
        }
    }

}