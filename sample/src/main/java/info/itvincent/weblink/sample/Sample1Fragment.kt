package info.itvincent.weblink.sample

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import info.itvincent.weblink.WeblinkInterface
import kotlinx.android.synthetic.main.fragment_sample1.*

/**
 * @author zhongyongsheng
 */

class Sample1Fragment : Fragment() {

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
        var weblinkInterface = WeblinkInterface(webview)
        webview.addJavascriptInterface(weblinkInterface, "weblink")
        webview.loadUrl("http://www.baidu.com")
        return rootView
    }

    companion object {

        fun newIns(): Sample1Fragment {
            return Sample1Fragment()
        }
    }

}
