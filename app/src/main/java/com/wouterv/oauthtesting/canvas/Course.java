package com.wouterv.oauthtesting.canvas;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by wouter on 27-5-2016.
 */
public class Course implements Parcelable{
    private int id;
    private String name;
    private Date startsAt;
    private List<Assignment> assignments;
    private int point;

    public Course(JSONObject response) throws JSONException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        this.id = response.getInt("id");
        this.name = response.getString("name");
        this.startsAt = format.parse(response.getString("start_at"));
        this.point = new Random().nextInt(91) + 10;
    }

    public List<Assignment> getAssignments() { return assignments; }

    public void setAssignments(List<Assignment> assignments) { this.assignments = assignments; }

    public int getId() { return id; }

    public String getName() {
        return name;
    }

    public Date getStartsAt() {
        return startsAt;
    }

    public int getPoint() { return point; }

    public Assignment getRollCallAssignment() {
        for (Assignment a : assignments) {
            if (a.getName().toLowerCase().contains("rollcall")) {
                return a;
            }
        }
        return null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
