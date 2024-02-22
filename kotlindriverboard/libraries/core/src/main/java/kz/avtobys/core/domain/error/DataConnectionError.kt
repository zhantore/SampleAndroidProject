package kz.avtobys.core.domain.error

class DataConnectionError(
    override val code: DataConnectionErrorCode,
    override val underlying: Throwable? = null,
    override val devMessage: String? = null
): BaseError(code, underlying, devMessage) {

    override fun shortName(): String = "DC"
}

enum class DataConnectionErrorCode(override val code: Int): BaseErrorCode {
    NO_CONNECTION(0),
    TIMEOUT(1)
}