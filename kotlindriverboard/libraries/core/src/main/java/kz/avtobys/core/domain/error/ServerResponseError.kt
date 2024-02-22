package kz.avtobys.core.domain.error

class ServerResponseError(
    override val code: ServerResponseErrorCode,
    override val underlying: Throwable? = null
): BaseError(code, underlying) {
}

enum class ServerResponseErrorCode(override val code: Int): BaseErrorCode {
    INTERNAL_ERROR(500),
    BAD_GATEWAY(502);

    companion object {

        fun getErrorCodeOrDefault(
            code: Int,
            default: ServerResponseErrorCode = INTERNAL_ERROR
        ): ServerResponseErrorCode = values()
            .find { it.code == code }
            ?: default
    }
}