package kz.quranuiren

import android.net.http.SslError
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient

class AndroidWebViewManager(private val webView: WebView): WebViewManager {

    init {
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true // Enable JavaScript
        webSettings.cacheMode = WebSettings.LOAD_DEFAULT // Adjust caching
        webSettings.domStorageEnabled = true // Enable DOM storage
        webSettings.databaseEnabled = true
        webSettings.userAgentString = System.getProperty("https.agent")

        // Set custom WebViewClient to handle navigation within WebView
        webView.webViewClient = object : WebViewClient() {
            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                view?.reload()
            }

            override fun onReceivedSslError(
                view: WebView?, handler: SslErrorHandler?, error: SslError?
            ) {
                handler?.proceed()
            }
        }

        // Set custom WebChromeClient for additional web features like alerts
        webView.webChromeClient = WebChromeClient()
    }

    override fun loadUrl(url: String) {
        webView.clearCache(true)
        webView.loadUrl(url)
    }

    override fun reload() {
        webView.reload()
    }

    override fun goBack() {
        if (webView.canGoBack()) webView.goBack()
    }
}