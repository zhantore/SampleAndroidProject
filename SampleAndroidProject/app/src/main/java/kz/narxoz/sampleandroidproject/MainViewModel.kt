package kz.narxoz.sampleandroidproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kz.narxoz.sampleandroidproject.model.PopulationInKz
import kz.narxoz.sampleandroidproject.model.Size
import kz.narxoz.sampleandroidproject.network.EgovDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainViewModel : ViewModel() {

    private var _uiStateFlow = MutableStateFlow(emptyList<PopulationInKz>())
    val uiStateFlow: StateFlow<List<PopulationInKz>?> = _uiStateFlow.asStateFlow()

    private val _uiSharedFlow = MutableSharedFlow<String>()
    val uiSharedFlow: SharedFlow<String> = _uiSharedFlow.asSharedFlow()

    private var retrofitClass: Retrofit? = null
    private var egovQuery: EgovDataSource? = null

    init {
        retrofitClass = RetrofitClass().getRetrofit()
        egovQuery = retrofitClass?.create(EgovDataSource::class.java)
        getData()
        request()
    }

    private fun request() {
        viewModelScope.launch(Dispatchers.IO) { // Coroutine
            egovQuery?.getPopulationInKz(Size(5))
                ?.enqueue(object : Callback<List<PopulationInKz>> {
                    override fun onResponse(
                        p0: Call<List<PopulationInKz>>,
                        p1: Response<List<PopulationInKz>>
                    ) {
                        _uiStateFlow.value = p1.body() ?: emptyList()
                    }

                    override fun onFailure(p0: Call<List<PopulationInKz>>, p1: Throwable) {
                        val error = p1.message.orEmpty()
                    }
                })
        }
    }

    private fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            // ........calculation

            withContext(Dispatchers.Main) {
//                _uiStateFlow.value = "daknflkasklfjsak"
            }
        }
    }
}