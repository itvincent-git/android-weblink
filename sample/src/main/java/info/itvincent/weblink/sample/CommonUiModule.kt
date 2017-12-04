package info.itvincent.weblink.sample

import android.webkit.WebView
import android.widget.Toast
import com.google.gson.Gson
import info.itvincent.weblink.WeblinkModule

/**
 * Common UI web method
 * @author zhongyongsheng
 */
object CommonUiModule : WeblinkModule {
    override val moduleName: String
        get() = "CommonUi"

    override fun invokeModule(webview: WebView?, cmd: String, parameters: String, callback: String, callbackFunction: (String, String) -> Unit) {
        when(cmd) {
            "toast" -> {
                val toastParam = Gson().fromJson(parameters, ToastParam::class.java)
                Toast.makeText(webview?.context, toastParam?.text,
                        if (toastParam.isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
            }
            "time" -> {
                callbackFunction.invoke(callback, Gson().toJson(TimeResult(System.currentTimeMillis())))
            }
        }
    }

    data class ToastParam(val text: String, val isLong: Boolean)

    data class TimeResult(val value: Long)
}