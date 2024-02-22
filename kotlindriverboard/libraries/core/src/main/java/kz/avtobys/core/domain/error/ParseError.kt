package kz.avtobys.core.domain.error

class ParseError(
    override val code: ParseErrorCode,
    override val underlying: Throwable? = null,
    override val devMessage: String? = null
): BaseError(code, underlying, devMessage) {

    override fun shortName(): String = "PA"
}

enum class ParseErrorCode(override val code: Int): BaseErrorCode {
    INVALID_JSON(0),
    INVALID_PARSE_CONFIG(1),
    ILLEGAL_STATE(2)
}