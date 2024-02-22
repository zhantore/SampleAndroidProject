package kz.avtobys.common.utils.ext

inline fun tryToCall(block: () -> Unit) {
    tryToGetOrNull { block() }
}

inline fun <T> tryToGetOrNull(block: () -> T?): T? {
    return try {
        block()
    } catch (e: Throwable) {
        null
    }
}