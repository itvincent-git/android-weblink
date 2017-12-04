package info.itvincent.weblink.sample

import android.webkit.WebView
import android.widget.Toast
import com.google.gson.Gson
import info.itvincent.weblink.WeblinkModule

/**
 *
 * @author zhongyongsheng
 */
object CommonUiModule : WeblinkModule {
    override val moduleName: String
        get() = "CommonUi"

    override fun invokeModule(webview : WebView?, cmd: String, parameters: String, callback: (String, String) -> Unit) {
        when(cmd) {
            "toast" -> {
                val toastParam = Gson().fromJson(parameters, ToastParam::class.java)
                Toast.makeText(webview?.context, cmd, if (toastParam.isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
            }
        }
    }

    data class ToastParam(val isLong: Boolean)
}