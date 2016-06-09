package com.wouterv.oauthtesting;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class ResponseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response);
        Log.d("aaa", getIntent().getData().toString());

        String link = getAccesTokenFromString(getIntent().getData().toString());

        ((TextView) findViewById(R.id.acces_token)).setText(link);
        new JsonHandler().getJSON("https://api.fhict.nl/Canvas/Courses/me",link,this, JsonHandler.Mode.Courses);
    }


    public static String getAccesTokenFromString(String link) {
        link = link.replace("fhictnyx://nyxcallback/#access_token=", "");
        int bb = link.indexOf('&');
        Log.d("bb", "" + bb);
        return link.substring(0, link.indexOf('&'));
    }
}
