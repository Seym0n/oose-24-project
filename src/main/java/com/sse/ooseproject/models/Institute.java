package com.sse.ooseproject.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "institute")
public class Institute extends OrganizationalUnit {

    private String providesStudySubject;

    @OneToMany(mappedBy = "institute")
    private List<Chair> chairList;

    public Institute(){

    }

    public Institute(String pName, String pProvidesStudySubject){
        super(pName);
        this.providesStudySubject = pProvidesStudySubject;
    }

    // Getter & Setter methods

    public String getProvidesStudySubject() {
        return providesStudySubject;
    }

    public void setProvidesStudySubject(String providesStudySubject) {
        this.providesStudySubject = providesStudySubject;
    }

    public List<Chair> getChairList() {
        return chairList;
    }

    public void setChairList(List<Chair> chairList) {
        this.chairList = chairList;
    }
}
