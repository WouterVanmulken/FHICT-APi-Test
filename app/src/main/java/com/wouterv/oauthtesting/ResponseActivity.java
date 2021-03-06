package com.wouterv.oauthtesting;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.wouterv.oauthtesting.canvas.Assignment;
import com.wouterv.oauthtesting.canvas.Course;
import com.wouterv.oauthtesting.json.IResultJsonArray;
import com.wouterv.oauthtesting.json.VolleyServiceJsonArray;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class ResponseActivity extends AppCompatActivity implements IResultJsonArray{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response);

        String accesToken = getAccesTokenFromString(getIntent().getData().toString());
        Log.d("access_token", accesToken);
        Config.getInstance().setAccess_token(accesToken); //should probrably use sharedPreferences for this
        new VolleyServiceJsonArray(this,getApplicationContext()).getCourses();

    }


    public static String getAccesTokenFromString(String link) {
        link = link.replace("fhictnyx://nyxcallback/#access_token=", "");//maybe set the redirect link in a shared preference ?
        int bb = link.indexOf('&');
        return link.substring(0, link.indexOf('&'));
    }

    @Override
    public void notifySuccess(String requestType, JSONArray response) {
        List<Course> courses = new ArrayList<>();
        for (int i =0; i<response.length();i++){
            try {
                courses.add(new Course(response.getJSONObject(i)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String course_list ="";
        final Activity currentActivity =this;
        LinearLayout ll = (LinearLayout)findViewById(R.id.ll);

        for (final Course c :courses) {

            Button btn = new Button(this);
            btn.setText(c.getName());
            btn.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(currentActivity, Assignment.class);
                    i.putExtra("course", c);
                }
            });
            ll.addView(btn);


            course_list += c.getName() + "\n";
        }
        ll.invalidate();

        ((TextView) findViewById(R.id.acces_token)).setText(course_list);

    }
    @Override
    public void notifyError(String requestType, VolleyError error) {
        Log.d("Volley Error", error.getLocalizedMessage());
    }
}
