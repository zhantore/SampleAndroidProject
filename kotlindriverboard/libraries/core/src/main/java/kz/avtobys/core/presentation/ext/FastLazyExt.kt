package kz.avtobys.core.presentation.ext

import kotlin.LazyThreadSafetyMode.NONE

fun <T> fastLazy(initializer: () -> T) = lazy(NONE, initializer)