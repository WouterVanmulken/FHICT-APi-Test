package com.wouterv.oauthtesting;

import android.app.Activity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wouter on 27-5-2016.
 * All of this code should be replaced by Sanders implementaion of this.
 */
public class JsonHandler {

    public void getJSON(String link,String acces_token, final Activity activity, final Mode mode) {
        Response.Listener<JSONObject> succesListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("response", response.toString());
            }
        };

        Response.ErrorListener errorListerner = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("fuuuuuuuu", error.toString());
            }
        };

        int method = Request.Method.GET;
        JSONObject request = null;
        Log.d("test",link+"?access_token="+acces_token);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(method, link+"?access_token="+acces_token, request, succesListener, errorListerner);
        RequestQueue queue = Volley.newRequestQueue(activity.getApplicationContext());
        queue.add(jsObjRequest);
    }
    public enum Mode{
        Courses,
        Assignments,
        Submissions
    }

}
