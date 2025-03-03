package kz.quranuiren

import platform.Foundation.NSURL
import platform.Foundation.NSURLRequest
import platform.WebKit.WKNavigationDelegateProtocol
import platform.WebKit.WKWebView
import platform.WebKit.WKWebViewConfiguration
import platform.WebKit.WKWebsiteDataStore
import platform.WebKit.javaScriptEnabled
import platform.darwin.NSObject

class IOSWebViewManager(private val webView: WKWebView): WebViewManager {

    init {
        val config = WKWebViewConfiguration()
        config.preferences.javaScriptEnabled = true
        config.websiteDataStore = WKWebsiteDataStore.defaultDataStore() // Cache settings

        // Apply configuration to the WKWebView
        webView.configuration.preferences.javaScriptEnabled = true
        webView.navigationDelegate = object : NSObject(), WKNavigationDelegateProtocol {
            // Override methods to customize navigation behavior if needed
        }
    }

    override fun loadUrl(url: String) {
        val nsUrl = NSURL(string = url)
        val request = NSURLRequest(uRL = nsUrl)
        webView.loadRequest(request)
    }

    override fun reload() {
        webView.reload()
    }

    override fun goBack() {
        if (webView.canGoBack()) webView.goBack()
    }
}