package kz.narxoz.sampleandroidproject

import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast

private const val TAG = "SampleBroadcastReceiver"

class SampleBroadcastReceiver : BroadcastReceiver() {

    override fun peekService(myContext: Context?, service: Intent?): IBinder {
        return super.peekService(myContext, service)
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
            val state = intent?.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR)
            when (state) {
                BluetoothAdapter.STATE_OFF -> {
                    Log.d(TAG, "STATE_OFF - $state")
                }

                BluetoothAdapter.STATE_ON -> {
                    Log.d(TAG, "STATE_ON - $state")
                }

                BluetoothAdapter.STATE_TURNING_OFF -> {
                    Log.d(TAG, "STATE_TURNING_OFF - $state")
                }

                BluetoothAdapter.STATE_TURNING_ON -> {
                    Log.d(TAG, "STATE_TURNING_ON - $state")
                }
            }
        } else if (intent?.action.equals("com.example.broadcast.MY_NOTIFICATION")) {
            Log.d(TAG, "MY_NOTIFICATION_BROADCAST")
            Toast.makeText(context, "MY_NOTIFICATION_BROADCAST", Toast.LENGTH_LONG).show()
        }
    }
}