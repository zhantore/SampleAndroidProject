package kz.narxoz.sampleandroidproject

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClass {

    fun getRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://data.egov.kz/")
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .build()
}