package kz.avtobys.core.presentation.ext

inline fun <reified T> Any?.castOrNull(): T? {
    return this as? T
}

fun Any?.isNull(): Boolean = this == null

fun Any?.isNotNull(): Boolean = this != null