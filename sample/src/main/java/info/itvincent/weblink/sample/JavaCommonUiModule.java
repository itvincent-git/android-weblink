package info.itvincent.weblink.sample;

import android.webkit.WebView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import info.itvincent.weblink.WeblinkModule;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/**
 * Common UI web method, Java version
 * @author zhongyongsheng
 */

public class JavaCommonUiModule implements WeblinkModule {

    private static final JavaCommonUiModule sInstance = new JavaCommonUiModule();

    public static JavaCommonUiModule getInstance() {
        return sInstance;
    }

    private JavaCommonUiModule() {}

    @NotNull
    @Override
    public String getModuleName() {
        return "CommonUi";
    }

    @Override
    public void invokeModule(@Nullable WebView webview, @NotNull String cmd, @NotNull String parameters,
                             @NotNull String callback,
                             @NotNull Function2<? super String, ? super String, Unit> callbackFunction) {
        switch (cmd) {
            case "toast":
                CommonUiModule.ToastParam toastParam = new Gson().fromJson(parameters, CommonUiModule.ToastParam.class);
                Toast.makeText(webview.getContext(), toastParam.getText(),
                        toastParam.isLong() ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT)
                    .show();
                break;
            case "time":
                callbackFunction.invoke(callback, new Gson().toJson(
                        new CommonUiModule.TimeResult(System.currentTimeMillis())));
                break;
        }

    }
}
