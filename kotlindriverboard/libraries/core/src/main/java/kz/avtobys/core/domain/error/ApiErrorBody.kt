package kz.avtobys.core.domain.error

import com.google.gson.annotations.SerializedName

data class ApiErrorBody(
    val code: String,
    val message: String,
    val serviceError: String? = null,
    val data: ApiErrorDataBody? = null,
)

class ApiErrorDataBody(
    @SerializedName("description")
    val description: String?
)