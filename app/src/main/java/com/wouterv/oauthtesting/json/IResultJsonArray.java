package com.wouterv.oauthtesting.json;

import com.android.volley.VolleyError;

import org.json.JSONArray;

/**
 * Interface for passing Volley results
 *
 * Created by Sander van Zelst on 25-5-2016.
 */
public interface IResultJsonArray {
    /**
     * Method that contains the JSONArray from a successful request
     * @param requestType
     * @param response
     */
    void notifySuccess(String requestType, JSONArray response);

    /**
     * Method that contains the VolleyError from an unsuccessful request
     * @param requestType
     * @param error
     */
    void notifyError(String requestType, VolleyError error);
}
