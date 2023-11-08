package com.example.prm392_finalproject.VNPAY;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ZoomButtonsController;

import com.example.prm392_finalproject.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class VNPay extends AppCompatActivity {
    private WebView wvContent;
    private String url = "";
    private String scheme = "";
    private String tmn_code = "";
    private boolean is_sandbox = false;
    private VNP_BankEntity[] entity_Response;
    private ProgressDialog dialog;
    private LinearLayout webLayount;
    private static VNP_SdkCompletedCallback sdkCompletedCallback;
    String Url = "https://pay.vnpay.vn/qrpayauth/api/sdk/get_qrpay_support/";
    String Url_sandbox = "https://sandbox.vnpayment.vn/qrpayauth/api/sdk/get_qrpay_support";
    String Host = "pay.vnpay.vn";
    String Host_sandbox = "sandbox.vnpayment.vn";

    public VNPay() {
    }
    public static void setSdkCompletedCallback(VNP_SdkCompletedCallback sdkCompletedCallback) {
        VNPay.sdkCompletedCallback = sdkCompletedCallback;
    }

    public void onBackPressed() {
        super.onBackPressed();
        if (sdkCompletedCallback != null) {
            sdkCompletedCallback.sdkAction("AppBackAction");
        }

        this.finish();
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vnpay);
        this.webLayount = (LinearLayout)this.findViewById(R.id.vnpayLayout);
        try {
            Bundle bundle = this.getIntent().getExtras();
            if (bundle != null) {
                if (bundle.containsKey("url")) {
                    this.url = bundle.getString("url");
                    if (TextUtils.isEmpty(this.url)) {
                        Toast.makeText(this, "Thiếu tham số", Toast.LENGTH_LONG).show();
                        this.setResult(-1);
                        this.finish();
                    }

                    Log.wtf("SDK", this.url);
                } else {
                    Toast.makeText(this, "Thiếu tham số", Toast.LENGTH_LONG).show();
                    this.setResult(-1);
                    this.finish();
                }

                if (bundle.containsKey("scheme")) {
                    this.scheme = bundle.getString("scheme");
                    if (TextUtils.isEmpty(this.scheme)) {
                        Toast.makeText(this, "Thiếu tham số", Toast.LENGTH_LONG).show();
                        this.setResult(-1);
                        this.finish();
                    } else {
                        if (!this.scheme.endsWith("://")) {
                            this.scheme = this.scheme + "://sdk";
                        }

                        Log.wtf("SDK", this.scheme);
                    }
                }
                if (bundle.containsKey("tmn_code")) {
                    this.tmn_code = bundle.getString("tmn_code");
                    if (TextUtils.isEmpty(this.tmn_code)) {
                        Toast.makeText(this, "Thiếu tham số", Toast.LENGTH_LONG).show();
                        this.setResult(-1);
                        this.finish();
                    }

                    Log.wtf("SDK", this.tmn_code);
                } else {
                    Toast.makeText(this, "Thiếu tham số", Toast.LENGTH_LONG).show();
                    this.setResult(-1);
                    this.finish();
                }

                if (bundle.containsKey("is_sandbox")) {
                    this.is_sandbox = bundle.getBoolean("is_sandbox");
                    Log.wtf("SDK is_sandbox", this.is_sandbox ? "true" : "false");
                }

                this.wvContent = (WebView) this.findViewById(R.id.webview);
                this.wvContent.getSettings().setJavaScriptEnabled(true);
                this.wvContent.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
                this.wvContent.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
                this.wvContent.setInitialScale(1);
                this.wvContent.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
                this.wvContent.getSettings().setLoadWithOverviewMode(true);
                this.wvContent.getSettings().setUseWideViewPort(true);
                this.wvContent.getSettings().setSupportZoom(true);
                this.wvContent.getSettings().setDomStorageEnabled(true);
                this.wvContent.getSettings().setBuiltInZoomControls(true);
                if (Build.VERSION.SDK_INT >= 11) {
                    (new Runnable() {
                        @TargetApi(11)
                        public void run() {
                            VNPay.this.wvContent.getSettings().setDisplayZoomControls(false);
                        }
                    }).run();
                } else {
                    try {
                        ZoomButtonsController zoom_controll = (ZoomButtonsController) this.wvContent.getClass().getMethod("getZoomButtonsController").invoke(this.wvContent);
                        zoom_controll.getContainer().setVisibility(View.GONE);
                    } catch (IllegalAccessException var) {
                        var.printStackTrace();
                    } catch (InvocationTargetException var) {
                        var.printStackTrace();
                    } catch (NoSuchMethodException var) {
                        var.printStackTrace();
                    }
                }

                this.wvContent.setWebViewClient(new myWebClient());
                this.wvContent.loadUrl(this.url);
            }


            try {
                if (this.dialog == null || !this.dialog.isShowing()) {
                    this.dialog = new ProgressDialog(this);
                }

                this.dialog.setMessage("Xin doi trong giay lat");
                this.dialog.show();
            } catch (Exception var) {
                Log.wtf("SDK", var.getMessage());
            }

            (new Thread(new Runnable() {
                public void run() {
                    try {
                        OkHttpClient client = (new OkHttpClient()).newBuilder().connectTimeout(30L, TimeUnit.SECONDS).writeTimeout(80L, TimeUnit.SECONDS).readTimeout(80L, TimeUnit.SECONDS).build();
                        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
                        RequestBody body = RequestBody.create(mediaType, "tmn_code=" + VNPay.this.tmn_code + "&os_type=ANDROID");
                        Request request = (new Request.Builder()).url(VNPay.this.is_sandbox ? VNPay.this.Url_sandbox : VNPay.this.Url).post(body).addHeader("Content-Type", "application/x-www-form-urlencoded").addHeader("cache-control", "no-cache,no-cache").addHeader("Accept", "*/*").addHeader("Host", VNPay.this.is_sandbox ? VNPay.this.Host_sandbox : VNPay.this.Host).addHeader("accept-encoding", "gzip, deflate").addHeader("content-length", "33").addHeader("Connection", "keep-alive").build();
                        Response response = client.newCall(request).execute();
                        Gson gson = (new GsonBuilder()).disableHtmlEscaping().create();
                        StringReader stringReader = new StringReader(response.body().string());
                        JsonReader reader = new JsonReader(stringReader);
                        VNPay.this.entity_Response = (VNP_BankEntity[])gson.fromJson(String.valueOf(reader), VNP_BankEntity[].class);
                        Log.wtf("Tag", "Success");
                        if (VNPay.this.dialog != null) {
                            if (VNPay.this.dialog.isShowing()) {
                                VNPay.this.dialog.dismiss();
                            }

                            VNPay.this.dialog = null;
                        }
                    } catch (Exception var) {
                        var.printStackTrace();
                        Log.wtf("SDK", var.getMessage());
                        if (VNPay.this.dialog != null) {
                            if (VNPay.this.dialog.isShowing()) {
                                VNPay.this.dialog.dismiss();
                            }

                            VNPay.this.dialog = null;
                        }
                    }

                }
            })).start();
        } catch (Exception var) {
            var.printStackTrace();
            Log.wtf("SDK", var.getMessage());
            if (this.dialog != null) {
                if (this.dialog.isShowing()) {
                    this.dialog.dismiss();
                }

                this.dialog = null;
            }
        }
    }

    private static Uri addUriParameter(Uri uri, String key, String newValue) {
        Set<String> params = uri.getQueryParameterNames();
        Uri.Builder newUri = uri.buildUpon().clearQuery();
        Iterator var5 = params.iterator();

        while(var5.hasNext()) {
            String param = (String)var5.next();
            newUri.appendQueryParameter(param, uri.getQueryParameter(param));
        }

        newUri.appendQueryParameter(key, newValue);
        return newUri.build();
    }
    class myWebClient extends WebViewClient {
        private final int PAGE_REDIRECTED = 2;
        private final int PAGE_STARTED = 1;


        @TargetApi(23)
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            VNPay.this.webLayount.setVisibility(View.GONE);
        }

        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            VNPay.this.webLayount.setVisibility(View.VISIBLE);
            String vnp_TransactionStatus;
            try {
                vnp_TransactionStatus = getParameterFromUrl(failingUrl,"vnp_TransactionStatus");
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
            if(vnp_TransactionStatus.equals("00")) {
                if (VNPay.sdkCompletedCallback != null) {
                    VNPay.sdkCompletedCallback.sdkAction("SuccessBackAction");

                }
            }
            else {
                if (VNPay.sdkCompletedCallback != null) {
                    VNPay.sdkCompletedCallback.sdkAction("FaildBackAction");
                }
            }

            VNPay.this.finish();
        }
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            VNPay.this.webLayount.setVisibility(View.VISIBLE);
            Intent intent;
            Uri uri;
            if (!TextUtils.isEmpty(url) && url.startsWith("intent://")) {
                try {
                    uri = Uri.parse(url);
                    if (!TextUtils.isEmpty(VNPay.this.scheme)) {
                        uri = VNPay.addUriParameter(uri, "newcallbackurl", VNPay.this.scheme);
                    }

                    new Intent();
                    intent = Intent.parseUri(uri.toString(), Intent.URI_INTENT_SCHEME);
                    if (intent != null) {
                        boolean authenticate = false;

                        try {
                            if (VNPay.this.entity_Response == null) {
                                authenticate = true;
                            } else {
                                VNP_BankEntity[] var6 = VNPay.this.entity_Response;
                                int var7 = var6.length;

                                for(int var8 = 0; var8 < var7; ++var8) {
                                    VNP_BankEntity bankEntity = var6[var8];
                                    if (bankEntity.andr_scheme.equals(intent.getScheme())) {
                                        authenticate = true;
                                        break;
                                    }
                                }
                            }
                        } catch (Exception var12) {
                            var12.printStackTrace();
                            Log.wtf("SDK", var12.getMessage());
                            authenticate = true;
                        }

                        if (authenticate) {
                            view.stopLoading();

                            try {
                                VNPay.this.startActivity(intent);
                                if (VNPay.sdkCompletedCallback != null) {
                                    VNPay.sdkCompletedCallback.sdkAction("CallMobileBankingApp");
                                }

                                VNPay.this.finish();
                            } catch (Exception var11) {
                                String appPackageName = intent.getPackage();

                                try {
                                    VNPay.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + appPackageName)));
                                } catch (ActivityNotFoundException var10) {
                                    VNPay.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                                }
                            }
                        } else {
                            Toast.makeText(VNPay.this, "This bank is not support", Toast.LENGTH_LONG).show();
                        }

                        return true;
                    }
                } catch (Exception var13) {
                    var13.printStackTrace();
                    Log.wtf("SDK", var13);
                }

                return true;
            } else if (url.contains("cancel.sdk.merchantbackapp")) {
                if (VNPay.sdkCompletedCallback != null) {
                    VNPay.sdkCompletedCallback.sdkAction("WebBackAction");
                }

                VNPay.this.finish();
                return true;
            } else if (url.contains("fail.sdk.merchantbackapp")) {
                if (VNPay.sdkCompletedCallback != null) {
                    VNPay.sdkCompletedCallback.sdkAction("FaildBackAction");
                }

                VNPay.this.finish();
                return true;
            } else if (url.contains("success.sdk.merchantbackapp")) {
                if (VNPay.sdkCompletedCallback != null) {
                    VNPay.sdkCompletedCallback.sdkAction("SuccessBackAction");
                }

                VNPay.this.finish();
                return true;
            } else if (url.startsWith("tel:")) {
                intent = new Intent("android.intent.action.DIAL", Uri.parse(url));
                VNPay.this.startActivity(intent);
                return true;
            } else if (url.startsWith("mailto:")) {
                Intent i = new Intent("android.intent.action.SENDTO", Uri.parse(url));
                VNPay.this.startActivity(i);
                return true;
            } else if (!url.startsWith("http")) {
                uri = Uri.parse(url);
                Intent ix = new Intent("android.intent.action.VIEW", uri);
                VNPay.this.startActivity(ix);
                VNPay.this.finish();
                return true;
            } else {
                view.loadUrl(url);
                return true;
            }
        }

    }
    private static String getParameterFromUrl(String url, String parameterName) throws URISyntaxException {
        URI uri = new URI(url);
        String query = uri.getQuery();

        Map<String, String> parameters = new HashMap<>();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            if (idx >= 0) {
                String key = pair.substring(0, idx);
                String value = pair.substring(idx + 1);
                parameters.put(key, value);
            }
        }

        return parameters.get(parameterName);
    }
}