package org.darkbyte.nithtpo.student;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;

import org.darkbyte.nithtpo.R;

public class placement_details extends AppCompatActivity {
WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement_details);
        webView=(WebView)findViewById(R.id.placementdetail);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.canGoBack();
        webView.canGoForward();
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl("http://nith.indiacareerportal.com/home.php");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                webView.reload();
            }
        },1000);
    }
}
