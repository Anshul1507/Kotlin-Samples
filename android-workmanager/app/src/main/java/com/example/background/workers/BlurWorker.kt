package com.example.background.workers

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.background.R
import timber.log.Timber

class BlurWorker(ctx: Context,params: WorkerParameters) : Worker(ctx,params) {
    override fun doWork(): Result {
        val appContext = applicationContext
        makeStatusNotification("Blurring Image",appContext)

        return try {
            val picture = BitmapFactory.decodeResource(
                    appContext.resources, R.drawable.test
            )

            val ouput = blurBitmap(picture,appContext)

            val outputUri = writeBitmapToFile(appContext,ouput)
            makeStatusNotification("Output is $outputUri",appContext)
            Result.success()
        }catch (throwable: Throwable){
            Timber.e(throwable,"Error applying blur")
            Result.failure()
        }
    }

}