package kz.avtobys.core.presentation.ext

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.launch
import kz.avtobys.core.domain.mapper.BaseErrorMapper
import kz.avtobys.core.data.DefaultBaseErrorMapper
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun CoroutineScope.launchSafe(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    onError: (suspend (Throwable) -> Unit)? = null,
    finally: () -> Unit = { },
    baseErrorMapperProvider: () -> BaseErrorMapper = { DefaultBaseErrorMapper() },
    block: suspend CoroutineScope.() -> Unit,
) = launch(context, start) {
    try {
        block()
    } catch (ex: Throwable) {
        onError?.invoke(baseErrorMapperProvider().map(ex))
    } finally {
        finally.invoke()
    }
}