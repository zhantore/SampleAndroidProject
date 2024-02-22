package kz.avtobys.core.domain.error

class UnauthorizedError(
    override val code: UnauthorizedErrorCode = UnauthorizedErrorCode.UNAUTHORIZED,
    override val underlying: Throwable? = null
): BaseError(code, underlying) {

    override fun shortName(): String = "UA"
}

enum class UnauthorizedErrorCode(override val code: Int): BaseErrorCode {
    UNAUTHORIZED(0)
}