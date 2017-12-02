package info.itvincent.weblink.sample

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

/**
 * @author zhongyongsheng
 */

class Sample2Fragment : Fragment(), NameableFragment {
    override fun getFragmentName(): String {
        return "Sample2"
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
        val rootView = inflater!!.inflate(R.layout.fragment_sample2, container, false)
        val btn1 = rootView.findViewById<View>(R.id.btn1) as Button
        val btn2 = rootView.findViewById<View>(R.id.btn2) as Button
        btn1.setOnClickListener(mOnClickListener)
        btn2.setOnClickListener(mOnClickListener)


        return rootView
    }

    companion object {

        private val TAG = "Sample1Fragment"

        fun newIns(): Sample2Fragment {
            return Sample2Fragment()
        }
    }

}