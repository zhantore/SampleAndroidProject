package kz.narxoz.sampleandroidproject

import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.os.IBinder
import java.util.Timer
import java.util.TimerTask

class VolumeService : Service() {

    private lateinit var timer: Timer
    private lateinit var volumeTimerTask: TimerTask
    private lateinit var volumeManager: AudioManager

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        volumeManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        timer = Timer()
        volumeTimerTask = object : TimerTask() {
            override fun run() {
                volumeManager.setStreamVolume(AudioManager.STREAM_MUSIC, 100, AudioManager.FLAG_SHOW_UI)
            }
        }
        timer?.schedule(volumeTimerTask, 0, 5000L) // 5 sec period
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
        volumeTimerTask?.cancel()
    }
}