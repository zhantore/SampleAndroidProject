package kz.narxoz.sampleandroidproject.network

import kz.narxoz.sampleandroidproject.model.PopulationInKz
import kz.narxoz.sampleandroidproject.model.Size
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface EgovDataSource {

    @GET("proxy/kazakstan_respublikasy_halkyny2?apiKey=2b075120743042c7a67c86b09123382e")
    suspend fun getPopulationInKz(@Query("source")  size: Size): Call<List<PopulationInKz>>


//    sdafj
}