package kz.quranuiren

interface WebViewManager {
    fun loadUrl(url: String)
    fun reload()
    fun goBack()
}