package com.example.background.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.background.OUTPUT_PATH
import timber.log.Timber
import java.io.File

class CleanupWorker(ctx:Context,params:WorkerParameters): Worker(ctx,params) {
    override fun doWork(): Result {

        makeStatusNotification("Cleaning up old temporary files",applicationContext)
        sleep()

        return try {
            val outputDirectory = File(applicationContext.filesDir, OUTPUT_PATH)
            if(outputDirectory.exists()){
                val entries = outputDirectory.listFiles()
                if(entries != null){
                    for(entry in entries){
                       val name = entry.name
                        if(name.isNotEmpty() && name.endsWith(".png")){
                            val deleted = entry.delete()
                            Timber.i("Deleted $name - $deleted")
                        }
                    }
                }
            }
            Result.success()
        }catch (exception: Exception){
            Timber.e(exception)
            Result.failure()
        }
    }

}