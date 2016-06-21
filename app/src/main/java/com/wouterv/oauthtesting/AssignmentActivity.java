package com.wouterv.oauthtesting;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.wouterv.oauthtesting.canvas.Assignment;
import com.wouterv.oauthtesting.canvas.Course;
import com.wouterv.oauthtesting.json.IResultJsonArray;
import com.wouterv.oauthtesting.json.VolleyServiceJsonArray;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class AssignmentActivity extends AppCompatActivity implements IResultJsonArray {
    Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
        course = getIntent().getParcelableExtra("course");
        new VolleyServiceJsonArray(this,getApplicationContext()).getAssignmentForCourse(course.getId());
    }

    @Override
    public void notifySuccess(String requestType, JSONArray response) {
        List<Assignment> assignments = new ArrayList<>();
        for (int i =0; i<response.length();i++){
            try {
                assignments.add(new Assignment(response.getJSONObject(i),course));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        final Activity currentActivity =this;
        for (final Assignment a :assignments) {
            LinearLayout ll = (LinearLayout)findViewById(R.id.ll);

            Button btn = new Button(this);
            btn.setText("Manual Add");
            btn.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(currentActivity, Assignment.class);
//                    i.putExtra("course", a);
                }
            });
            ll.addView(btn);


//            course_list += c.getName() + "\n";
        }
//        ((TextView) findViewById(R.id.acces_token)).setText(course_list);

    }

    @Override
    public void notifyError(String requestType, VolleyError error) {

    }
}
