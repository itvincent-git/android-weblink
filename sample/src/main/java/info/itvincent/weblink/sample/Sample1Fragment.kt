package info.itvincent.weblink.sample

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import info.itvincent.weblink.WeblinkInterface
import kotlinx.android.synthetic.main.fragment_sample1.view.*

/**
 * @author zhongyongsheng
 */

class Sample1Fragment : Fragment(), NameableFragment {

    override fun getFragmentName(): String {
        return "Base"
    }

    private val mOnClickListener = View.OnClickListener { v ->
        when (v.id) {
            R.id.btn1 -> {
            }
            R.id.btn2 -> {
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_sample1, container, false)
        var weblinkInterface = WeblinkInterface(rootView.webview)
        var web = rootView.webview;
        web.addJavascriptInterface(weblinkInterface, "weblink")
        web.webChromeClient = WebChromeClient()
        web.loadUrl("http://www.baidu.com")
        return rootView
    }

    companion object {

        fun newIns(): Sample1Fragment {
            return Sample1Fragment()
        }

    }

}

