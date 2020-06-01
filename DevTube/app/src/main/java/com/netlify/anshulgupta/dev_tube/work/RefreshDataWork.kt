package com.netlify.anshulgupta.dev_tube.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Operation.SUCCESS
import androidx.work.WorkerParameters
import com.netlify.anshulgupta.dev_tube.db.getDB
import com.netlify.anshulgupta.dev_tube.ui.Video
import com.netlify.anshulgupta.dev_tube.ui.VideoRepository
import retrofit2.HttpException

class RefreshDataWork (appContext: Context, params: WorkerParameters): CoroutineWorker(appContext,params) {

    companion object {
        const val WORK_NAME = "RefreshDataWork"
    }
    override suspend fun doWork(): Result {
        val database = getDB(applicationContext)
        val repository = VideoRepository(database)

        return try {
            repository.refreshVideos()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}