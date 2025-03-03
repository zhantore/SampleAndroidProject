package kz.narxoz.sampleandroidproject

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Looper
import android.util.Log
import android.widget.Toast
import java.util.Timer
import java.util.TimerTask

private const val TAG = "SampleService"

class SampleService : Service() {

    private val timer = Timer()
    private lateinit var timerTask: TimerTask
    private var timerCount = 0

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        timerTask = object : TimerTask() {
            override fun run() {
                Log.d(TAG, "Count: ${timerCount++}")
            }
        }
        timer.schedule(timerTask, 0, 1000L)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "SampleService is destroyed!")
        timer.cancel()
        timerTask.cancel()
    }
}