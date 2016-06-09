package com.wouterv.oauthtesting;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    String link = "http://identity.fhict.nl/connect/authorize?client_id=i311425-nyx&scope=fhict%20fhict_personal&response_type=token&redirect_uri=fhictnyx://nyxcallback";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i =  new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(i);
//        setContentView(R.layout.activity_main);

//        Intent intent = getIntent();

//        Uri data = intent.getData();
//        URL url = null;

//        try {
//            url = new URL(data.getScheme(), data.getHost(), data.getPath());
//            url = new URL(link);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        WebView webView = (WebView) findViewById(R.id.webView);
//        webView.loadUrl(url.toString());
//        webView.loadUrl(link);
    }
//    @Override
//    protected void onResume(){
//        super.onResume();
//        Intent intent = getIntent();
//        Log.d("aaa",intent.getData().toString());
//    }
//    @Override
//    protected void onNewIntent(Intent intent) {
//        Log.d("on new intent", intent.toString());
//        super.onNewIntent(intent);
//        Log.d("",intent.getData().toString());
//        dealWithResponse(intent);
//    }
//    private void dealWithResponse(Intent intent) {
//        Uri uri = intent.getData();
//        System.out.println("URI=" + uri);
//
//        if (uri != null) {
//            String acces_token = uri.getQueryParameter("acces_token");
//            System.out.println(acces_token);
////            ((TextView)findViewById(R.id.acces_token)).setText(acces_token);
////            authoriseNewUser(oauthVerifier);
//        }
//    }
}
