package kz.avtobys.core.domain.error

class ApiError(
    override val code: ApiErrorCode = ApiErrorCode.DEFAULT,
    override val underlying: Throwable? = null,
    override val devMessage: String? = null,
    override val message: String? = null,
    val backendErrorCode: String? = null,
    val data: ApiErrorData? = null,
): BaseError(code, underlying, message) {

    override fun shortName(): String  = "AP"
}

class ApiErrorData(
    val description: String?,
)

enum class ApiErrorCode(override val code: Int): BaseErrorCode {
    DEFAULT(0),
    UNEXPECTED(1),
    VALIDATION(2)
}