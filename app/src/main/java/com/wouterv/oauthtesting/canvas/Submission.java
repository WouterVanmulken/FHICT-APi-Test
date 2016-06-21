package com.wouterv.oauthtesting.canvas;

import java.util.Date;

/**
 * Created by wouter on 2-6-2016.
 */
public class Submission {
    private int id;
    private int score;
    private String grade;
    private Date submittedAt;
    private boolean late;
    private int pointsPossible;

    public Submission(int id, int score, String grade, int pointsPossible, Date submittedAt, boolean late) {
        this.id = id;
        this.score = score;
        this.grade = grade;
        this.pointsPossible = pointsPossible;
        this.submittedAt = submittedAt;
        this.late = late;
    }

    public int getScore() {
        return score;
    }

    public Date getSubmittedAt() {
        return submittedAt;
    }

    public boolean isLate() {
        return late;
    }

    public int getPointsPossible() { return pointsPossible; }

    public int getId() { return id; }

    public String getGrade() {
        return grade;
    }
}
