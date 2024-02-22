package kz.avtobys.core.domain.mapper

import com.google.gson.Gson
import kz.avtobys.core.domain.error.ApiErrorBody
import java.io.Reader

class ApiErrorBodyMapper(
    private val gson: Gson = Gson(),
) : Mapper<Reader, ApiErrorBody>() {

    override fun map(from: Reader): ApiErrorBody {
        return gson.fromJson(from, ApiErrorBody::class.java)
    }
}