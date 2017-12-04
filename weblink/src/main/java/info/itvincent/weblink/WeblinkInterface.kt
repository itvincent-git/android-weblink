package info.itvincent.weblink

import android.os.Build
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.ValueCallback
import android.webkit.WebView
import java.lang.ref.WeakReference

/**
 * weblink api 接入类
 * @author zhongyongsheng
 */
class WeblinkInterface(webview: WebView) {
    val TAG = "WeblinkInterface"
    val jsApiName = "weblink"
    val webviewRef: WeakReference<WebView>
    private val weblinkModules : MutableMap<String, WeblinkModule> = HashMap()

    init {
        webviewRef = WeakReference(webview)
        webview.addJavascriptInterface(this, jsApiName)
    }

    @JavascriptInterface
    fun invoke(module: String, cmd: String, parameters: String, callback: String) {
        try {
            weblinkModules[module]?.invokeModule(webviewRef.get(), cmd, parameters, callback, { a, b ->
                invokeCallback(a, b)
            })
        } catch (e: Exception) {
            Log.e(TAG, "invoke error", e);
        }
    }

    fun addModule(module: WeblinkModule) {
        weblinkModules.put(module.moduleName, module)
    }

    fun invokeCallback(callback: String, jsonParam: String) {
        try {
            val cmd = "javascript:try{window.$jsApiName.invokeWebMethod('$callback',$jsonParam)}catch(e){if(console)console.log(e)}"
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                webviewRef.get()?.post { webviewRef.get()?.loadUrl(cmd) }
            } else {
                webviewRef.get()?.post {webviewRef.get()?.evaluateJavascript(cmd, { Log.i(TAG, "invokeCallback return value : ${it}") })}
            }
        } catch (e: Exception) {
            Log.e(TAG, "invokeCallback error", e);
        }
    }
}