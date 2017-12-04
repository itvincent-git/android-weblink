package info.itvincent.weblink.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import info.itvincent.weblink.WeblinkInterface;

/**
 * @author zhongyongsheng
 */

public class SampleJavaFragment extends Fragment {

    private View mRootView;

    public static SampleJavaFragment newInstance() {
        return new SampleJavaFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_sample2, container, false);
        WebView webView = mRootView.findViewById(R.id.webview);
        WeblinkInterface weblinkInterface = new WeblinkInterface(webView);
        weblinkInterface.addModule(CommonUiModule.INSTANCE);
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        webView.loadUrl("file:///android_asset/sample.html");
        return mRootView;
    }
}
