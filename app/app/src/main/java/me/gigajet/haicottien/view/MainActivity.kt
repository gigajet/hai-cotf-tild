package me.gigajet.haicottien.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import me.gigajet.haicottien.R
import me.gigajet.haicottien.data.ChangeApi
import me.gigajet.haicottien.data.Changes
import me.gigajet.haicottien.data.Repository
import me.gigajet.haicottien.ui.Gui
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Calendar
import java.util.GregorianCalendar
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var view: ComposeView
    private var dbJob: Job? = null

    private var _repo: Repository? = null
    private val repo: Repository get() = _repo!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* free plan backend can have up to 60 seconds of delay. Timeout to let local db handle it */
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .build()
        val retrofit =
            Retrofit.Builder()
                .baseUrl(getString(R.string.BASE_URL)) /* declared in secrets.xml for private backend */
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        val changeApi = retrofit.create(ChangeApi::class.java)
        _repo = Repository(
            sp = this.getSharedPreferences("hai_cotf_tild", Context.MODE_PRIVATE),
            api = changeApi,
        )
        view = ComposeView(context = this)
        view.setContent {
            val (leftChanges, setLeftChanges) = remember { mutableStateOf(Changes.EMPTY) }
            val (rightChanges, setRightChanges) = remember { mutableStateOf(Changes.EMPTY) }
            LaunchedEffect("getFromDb") {
                val (lc, rc) = refreshContent()
                setLeftChanges(lc)
                setRightChanges(rc)
            }
            Gui(leftChanges, rightChanges, onRefresh = {
                Log.d("hai-cotf-tild", "onRefresh")
                lifecycleScope.launch {
                    val (lc, rc) = refreshContent()
                    setLeftChanges(lc)
                    setRightChanges(rc)
                }
            })
        }
        dbJob?.cancel()
        dbJob = lifecycle.coroutineScope.launch {
            refreshContent()
        }
        setContentView(view)
    }

    private suspend fun refreshContent(): Pair<Changes, Changes> {
        try {
            Log.d("hai-cotf-tild", "refreshContent")
            val now = GregorianCalendar()
            val year = now.get(Calendar.YEAR)
            val month = now.get(Calendar.MONTH) + 1

            return repo.getChanges(year, month)
        } catch (e: Exception) {
            Log.e("hai-cotf-tild", "refreshContent", e)
            return Changes.EMPTY to Changes.EMPTY
        }
    }

    override fun onDestroy() {
        _repo = null
        super.onDestroy()
    }
}