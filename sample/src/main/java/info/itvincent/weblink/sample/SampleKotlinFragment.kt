package info.itvincent.weblink.sample

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import info.itvincent.weblink.WeblinkInterface
import kotlinx.android.synthetic.main.fragment_sample1.view.*

/**
 * kt的demo
 * @author zhongyongsheng
 */

class SampleKotlinFragment : Fragment(){

    private val mOnClickListener = View.OnClickListener { v ->
        when (v.id) {
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_sample1, container, false)
        var web = rootView.webview;
        var weblinkInterface = WeblinkInterface(web)
        weblinkInterface.addModule(CommonUiModule)
        web.webChromeClient = WebChromeClient()
        web.settings.javaScriptEnabled = true
        web.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }

        //web.loadUrl("http://www.jianshu.com")
        web.loadUrl("file:///android_asset/sample.html")
        return rootView
    }

    companion object {

        fun newInstance(): SampleKotlinFragment {
            return SampleKotlinFragment()
        }

    }

}

