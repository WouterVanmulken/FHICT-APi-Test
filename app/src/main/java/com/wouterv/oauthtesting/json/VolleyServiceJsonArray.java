package com.wouterv.oauthtesting.json;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.wouterv.oauthtesting.Config;
import com.wouterv.oauthtesting.R;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for making GET or POST calls with Volley and returning a JSONArray
 * <p/>
 * copy pasted from Sander van Zelsrt-VolleyService on 10-6-2016.
 */
public class VolleyServiceJsonArray {

    private IResultJsonArray resultCallback = null;
    private Context context;
    private String requestType = null;
    private RequestQueue requestQueue;

    /**
     * @param resultCallback the IResult implementation
     * @param context        the current activity context
     */
    public VolleyServiceJsonArray(IResultJsonArray resultCallback, Context context) {
        this.resultCallback = resultCallback;
        this.context = context;
        this.requestQueue = Volley.newRequestQueue(context);
    }

    /**
     * Make a POST request
     *
     * @param url        the url of the api
     * @param jsonArray optional jsonObject to be send with the request
     */
    public void requestAccessToken(String url, JSONArray jsonArray, final String code) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, jsonArray, successListener, errorListener);
        requestQueue.add(jsonArrayRequest);
    }

    /**
     * Make a GET request
     *
     * @param url        the url of the api
     * @param jsonArray optional jsonObject to be send with the request
     */
    public void getDataVolley(String url, JSONArray jsonArray) {
        requestType = "GETCALL";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, jsonArray, successListener, errorListener) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers = new HashMap<>();
                headers.put("Authorization","Bearer "+ Config.getInstance().getAccess_token());
                return headers;
            }
        };
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonArrayRequest);
    }

    public void getCourses() {
        requestType = "GETCALL";
        String url = context.getString(R.string.course_list);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, successListener, errorListener) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers = new HashMap<>();
                headers.put("Authorization","Bearer "+ Config.getInstance().getAccess_token());
                return headers;
            }
        };
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonArrayRequest);
    }
    public void getAssignmentForCourse(int id) {
        requestType = "GETCALL";
        String url = String.format(context.getString(R.string.assignments_by_course_id),id+"");
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, successListener, errorListener) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers = new HashMap<>();
                headers.put("Authorization","Bearer "+ Config.getInstance().getAccess_token());
                return headers;
            }
        };
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonArrayRequest);
    }


    /**
     * The callback for a successful request
     */
    private Response.Listener<JSONArray> successListener = new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {
            resultCallback.notifySuccess(requestType, response);
        }
    };

    /**
     * The callback for a unsuccessful request
     */
    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            resultCallback.notifyError(requestType, error);
        }
    };
}
