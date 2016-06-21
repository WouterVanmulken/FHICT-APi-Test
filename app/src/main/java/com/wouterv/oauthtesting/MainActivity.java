package com.wouterv.oauthtesting;

import android.content.Intent;
import android.net.Uri;
import android.provider.Browser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    String clientId = "i311425-nyx";
    String scope = "fhict%20fhict_personal";
    String redirectUri = "fhictnyx://nyxcallback";
    String link = "http://identity.fhict.nl/connect/authorize?client_id=" + clientId + "&scope=" + scope + "&response_type=token&redirect_uri=" + redirectUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(i);
        finish();
    }
}
