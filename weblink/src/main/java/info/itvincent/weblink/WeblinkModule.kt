package info.itvincent.weblink

import android.webkit.WebView

/**
 * weblink module模块，通过扩展这个接口实现回调
 * @author zhongyongsheng
 */
interface WeblinkModule {
    /**
     * 模块名称
     */
    val moduleName: String

    /**
     * 模块调用的接口
     */
    fun invokeModule(webview: WebView?, cmd: String, parameters: String, callback: String,
                     callbackFunction: (String, String) -> Unit)
}