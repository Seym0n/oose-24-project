package com.sse.ooseproject.models;

import jakarta.persistence.*;

@Entity
@Table(name = "student")
public class Student extends Person {
    // Note: This class does not need its own id attribute as that will be derived.
    private long matNr;
    private String studySubject;

    public Student(){
        super();
    }

    public Student(String pFirstName, String pLastName, String pEmail, long pMatNr, String pStudySubject){
        super(pFirstName, pLastName, pEmail);
        this.matNr = pMatNr;
        this.studySubject = pStudySubject;
    }

    public long getMatNr() {
        return this.matNr;
    }

    public String getStudySubject() {
        return this.studySubject;
    }

    public void setMatNr(long matNr) {
        this.matNr = matNr;
    }

    public void setStudySubject(String studySubject) {
        this.studySubject = studySubject;
    }
}
