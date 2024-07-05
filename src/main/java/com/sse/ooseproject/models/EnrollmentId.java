package com.sse.ooseproject.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;

@Embeddable
public class EnrollmentId {

    @JoinColumn(name = "course_id")
    private Long course_id;

    @JoinColumn(name = "student_id")
    private Long student_id;

    public EnrollmentId(){

    }

    public EnrollmentId(long pCourseId, long pStudentId){
        this.course_id = pCourseId;
        this.student_id = pStudentId;
    }

    public long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(long course_id) {
        this.course_id = course_id;
    }

    public long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(long student_id) {
        this.student_id = student_id;
    }
}
