package kz.avtobys.core.domain.error

open class BaseError(
    open val code: BaseErrorCode,
    open val underlying: Throwable? = null,
    open val devMessage: String? = null
): Exception(devMessage, underlying) {

    open fun errorCode(): Int = code.code

    open fun shortName(): String = ""

    open fun errorCodeName(): String = code.toString()
}

interface BaseErrorCode {
    val code: Int
}