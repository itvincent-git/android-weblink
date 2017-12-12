# Android Weblink

Android与Webview进行通讯的框架，核心代码使用了kotlin来实现，Module可以使用Java或Kotlin实现。

Webview通过js调用Module的命令来调用App原生的代码，然后通过callback的方式来回调结果给js，方案原理同样可以迁移到iOS上实现，在我们实际的项目中已经用ObjectC实现了。



## Sample

* Webview中的js代码如下：

```js
//调用模块CommonUi下的toast命令，参数为`{text:'Hello World', isLong:false}`
window.weblink.invoke("CommonUi", "toast", "{text:'Hello World', isLong:false}", "");
```



* `CommonUi.kt`的代码如下：

```kotlin
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
        }
    }

    data class ToastParam(val text: String, val isLong: Boolean)

    data class TimeResult(val value: Long)
}
```



* Webview中的使用的代码如下：

```java
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
        WeblinkInterface weblinkInterface = new WeblinkInterface(webView);//初始化
        weblinkInterface.addModule(CommonUiModule.INSTANCE);//加入CommonUiModule
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
```



## 安装

#### Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

#### Step 2. Add the dependency
```
dependencies {
    compile 'com.github.itvincent-git:android-weblink:0.1'
}
```