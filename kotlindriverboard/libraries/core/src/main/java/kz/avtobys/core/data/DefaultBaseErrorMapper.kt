package kz.avtobys.core.data

import com.google.gson.JsonParseException
import kz.avtobys.core.domain.error.ApiError
import kz.avtobys.core.domain.error.ApiErrorBody
import kz.avtobys.core.domain.error.ApiErrorCode
import kz.avtobys.core.domain.error.ApiErrorData
import kz.avtobys.core.domain.error.ApiErrorDataBody
import kz.avtobys.core.domain.error.BaseError
import kz.avtobys.core.domain.error.DataConnectionError
import kz.avtobys.core.domain.error.DataConnectionErrorCode
import kz.avtobys.core.domain.error.ParseError
import kz.avtobys.core.domain.error.ParseErrorCode
import kz.avtobys.core.domain.error.ServerResponseError
import kz.avtobys.core.domain.error.ServerResponseErrorCode
import kz.avtobys.core.domain.error.UnauthorizedError
import kz.avtobys.core.domain.mapper.ApiErrorBodyMapper
import kz.avtobys.core.domain.mapper.BaseErrorMapper
import kz.avtobys.core.domain.mapper.Mapper
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.io.Reader
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import javax.xml.parsers.ParserConfigurationException

private const val HTTP_INTERNAL_ERROR_MAX_CODE = 599
private const val HTTP_UNPROCESSABLE_ENTITY = 422

class DefaultBaseErrorMapper(
    private val apiErrorBodyMapperProvider: () -> Mapper<Reader, ApiErrorBody> = { ApiErrorBodyMapper() },
) : BaseErrorMapper() {

    override fun map(from: Throwable): Throwable {
        return from.toBaseErrorOrElse()
    }

    private fun Throwable.toBaseErrorOrElse(): Throwable {
        return when (val error = this) {
            is HttpException -> when (val httpCode = error.code()) {
                HttpURLConnection.HTTP_UNAUTHORIZED -> UnauthorizedError(underlying = error)
                in HttpURLConnection.HTTP_INTERNAL_ERROR..HTTP_INTERNAL_ERROR_MAX_CODE -> ServerResponseError(
                    code = ServerResponseErrorCode.getErrorCodeOrDefault(httpCode),
                    underlying = error
                )

                HttpURLConnection.HTTP_BAD_REQUEST -> getApiError(error.response())
                    ?: ApiError(underlying = error, code = ApiErrorCode.UNEXPECTED)

                HTTP_UNPROCESSABLE_ENTITY -> ParseError(underlying = error, code = ParseErrorCode.ILLEGAL_STATE)
                else -> ApiError(underlying = error, code = ApiErrorCode.UNEXPECTED)
            }

            is IllegalStateException -> ParseError(
                underlying = error,
                code = ParseErrorCode.ILLEGAL_STATE
            )

            is ParserConfigurationException -> ParseError(
                underlying = error,
                code = ParseErrorCode.INVALID_PARSE_CONFIG
            )

            is JsonParseException -> ParseError(
                underlying = error,
                code = ParseErrorCode.INVALID_JSON
            )

            is IOException -> if (error.cause is SocketTimeoutException) {
                DataConnectionError(underlying = error, code = DataConnectionErrorCode.TIMEOUT)
            } else {
                DataConnectionError(
                    underlying = error,
                    code = DataConnectionErrorCode.NO_CONNECTION
                )
            }

            is BaseError -> error
            else -> error
        }
    }

    private fun getApiError(
        response: Response<*>?
    ): ApiError? {
        return try {
            val errorBody = response?.errorBody()
                ?: return null
            val apiErrorBody = apiErrorBodyMapperProvider().map(errorBody.charStream())
            ApiError(
                code = ApiErrorCode.VALIDATION,
                devMessage = apiErrorBody.serviceError,
                backendErrorCode = apiErrorBody.code,
                message = apiErrorBody.message,
                data = apiErrorBody.data?.toApiErrorData(),
            )
        } catch (e: Throwable) {
            ApiError(underlying = e, code = ApiErrorCode.UNEXPECTED)
        }
    }

    private fun ApiErrorDataBody.toApiErrorData() = ApiErrorData(
        description = this.description,
    )
}