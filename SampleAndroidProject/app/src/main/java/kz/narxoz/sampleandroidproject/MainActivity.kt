package kz.narxoz.sampleandroidproject

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kz.narxoz.sampleandroidproject.ui.theme.SampleAndroidProjectTheme
import java.lang.IllegalStateException

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {

    private lateinit var sampleService: Intent
    private lateinit var volumeService: Intent
    private lateinit var sampleBroadcastReceiver: BroadcastReceiver
    private val job = CoroutineScope(Dispatchers.IO).launch {}


    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleAndroidProjectTheme {
                // A surface container using the 'background' color from the theme
                Scaffold { paddingValues ->
                    Surface(modifier = Modifier.padding(paddingValues)) {
                        var count by remember {
                            mutableIntStateOf(0)
                        }
//                    Greeting(paddingValues, this)
//                    FractionExample(paddingValues = paddingValues)
                        LaunchedEffect(key1 = Unit) {
                            repeat(11) {
                                count = it
                                delay(1000L)
                            }
                        }

                        CountingView(count)
                    }
                }
            }
        }
//        sampleService = Intent(this, SampleService::class.java)
//        startService(sampleService)

//        sampleBroadcastReceiver = SampleBroadcastReceiver()
//        registerReceiver(sampleBroadcastReceiver, IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED))
    }

    override fun onStart() {
        super.onStart()
        volumeService = Intent(this, VolumeService::class.java)
        startService(volumeService)
    }

    override fun onDestroy() {
        super.onDestroy()
        stopService(volumeService)
//        stopService(sampleService)
//        unregisterReceiver(sampleBroadcastReceiver)
//        job.cancel()
    }

    suspend fun getResult(): Int = CoroutineScope(Dispatchers.IO).async {
//        try {
            var digit = 0
            for (i in 0..10) {
                digit = i
                delay(1000L)
                Log.d(TAG, i.toString())
            }
            digit
//        } finally {
//            println("Completed async() task")
//        }
    }.await()
}


@Composable
fun CountingView(count: Int) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = count.toString(),
            fontSize = 200.sp,
        )
    }
}

@Composable
fun FractionExample(paddingValues: PaddingValues) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Box(modifier = Modifier
            .fillMaxSize(0.5f)
            .background(Color.Red))
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Green))
//        Box(modifier = Modifier.fillMaxWidth().fillMaxHeight().background(Color.Yellow))
    }
}

@Composable
fun Greeting(paddingValues: PaddingValues, context: Context) {
    val studentList = listOf("Student1", "Student2")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.End,
    ) { // Horizontal
        items(studentList) { item ->
            Column {
                Text(
                    text = item
                )
                Box(
                    modifier = Modifier.fillMaxWidth()
                ){
                    AnimatedPreloader(modifier = Modifier
                        .size(150.dp)
                        .align(Alignment.Center))
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun BoxScope.AnimatedPreloader(modifier: Modifier = Modifier) {
    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.animated_space_shuttle)
    )

    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true
    )

    LottieAnimation(
        composition = preloaderLottieComposition,
        progress = preloaderProgress,
        modifier = modifier.align(Alignment.Center)
    )
}

@Composable
fun GreetingPreview(context: Context) {
    SampleAndroidProjectTheme {
        Greeting(PaddingValues(), context)
    }
}