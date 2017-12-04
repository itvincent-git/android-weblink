package info.itvincent.weblink

import android.os.Build
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.ValueCallback
import android.webkit.WebView
import java.lang.ref.WeakReference

/**
 *
 * @author zhongyongsheng
 */
class WeblinkInterface(webview: WebView) {
    val TAG = "WeblinkInterface"
    val JS_INTERFACE_NAME = "weblink"
    val webviewRef: WeakReference<WebView>
    private val weblinkModules : MutableMap<String, WeblinkModule> = HashMap()

    init {
        webviewRef = WeakReference(webview)
        webview.addJavascriptInterface(this, JS_INTERFACE_NAME)
    }

    @JavascriptInterface
    fun invoke(module: String, cmd: String, parameters: String, callback: String) {
        weblinkModules[module]?.invokeModule(webviewRef.get(), cmd, parameters, {a, b ->
            invokeCallback(a, b)
        })
    }

    fun addModule(module: WeblinkModule) {
        weblinkModules.put(module.moduleName, module)
    }

    fun invokeCallback(callback: String, jsonParam: String) {
        val cmd = "javascript:try{window.weblink.invokeWebMethod('${callback}',JSON.parse(${jsonParam}))}catch(e){if(console)console.log(e)}"
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            webviewRef.get()?.loadUrl(cmd)
        } else {
            webviewRef.get()?.evaluateJavascript(cmd, ValueCallback { Log.i(TAG, "invokeCallback return value : ${it}") })
        }
    }
}