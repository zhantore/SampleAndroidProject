package kz.innoforce.telegramwebview

interface WebViewManager {
    fun loadUrl(url: String)
    fun reload()
    fun goBack()
}