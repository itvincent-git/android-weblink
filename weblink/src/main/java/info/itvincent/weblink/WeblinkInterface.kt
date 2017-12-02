package info.itvincent.weblink

import android.webkit.JavascriptInterface
import android.webkit.WebView

/**
 *
 * @author zhongyongsheng
 */
class WeblinkInterface(val webview: WebView) {

    @JavascriptInterface
    fun invoke() {

    }
}