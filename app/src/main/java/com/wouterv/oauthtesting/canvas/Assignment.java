package com.wouterv.oauthtesting.canvas;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wouter on 27-5-2016.
 */
public class Assignment {
    private int id;
    private String description;
    private Date dueAt;
    private int pointsPossible;
//    private int courseId;
    private String name;
    private Submission submission;
    private Course course;

    public Assignment(JSONObject response,Course course) throws JSONException, ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        this.course = course;

        if (response.has("points_possible")) {

            this.id = response.getInt("id");
            if (response.has("description"))
                this.description = response.getString("description");
            if (response.has("name"))
                this.name = response.getString("name");
            if (response.has("due_at")) {
                this.dueAt = format.parse(response.getString("due_at"));
            }
//            if (response.has("course_id"))
//                this.courseId = response.getInt("course_id");

            this.pointsPossible = 100;



            if (response.has("submission")) {
                JSONObject submisssion = response.getJSONObject("submission");
                int id = submisssion.getInt("id");

                int score = -1;
                String grade = "";

                score = (int) (Math.random() * 50) + 50;
                if (submisssion.has("grade")) {
                    grade = submisssion.getString("grade");
                }
                Date submittedAt = null;
                if (submisssion.has("submitted_at"))
                    submittedAt = format.parse(submisssion.getString("submitted_at"));
                else if (submisssion.has("graded_at"))
                    submittedAt = format.parse(submisssion.getString("graded_at"));
                if (submittedAt == null) {
                    Log.e("submitted at is null on", this.getName());
                }
                boolean late = false;
                if (submisssion.has("late")) late = submisssion.getBoolean("late");
                this.submission = new Submission(id, score, grade, pointsPossible, submittedAt, late);
            }
        }
    }


    public Course getCourse() {
        return course;
    }
    public Submission getSubmission() {
        return submission;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Date getDueAt() {
        return dueAt;
    }

    public int getPointsPossible() {
        return pointsPossible;
    }

    public String getName() {
        return name;
    }

}
