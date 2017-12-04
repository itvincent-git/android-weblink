package info.itvincent.weblink

import android.webkit.WebView

/**
 *
 * @author zhongyongsheng
 */
interface WeblinkModule {
    val moduleName: String

    fun invokeModule(webview : WebView?, cmd: String, parameters: String, callback: (String, String) -> Unit)
}