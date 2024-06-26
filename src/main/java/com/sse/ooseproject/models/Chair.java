package com.sse.ooseproject.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "chair")
public class Chair extends OrganizationalUnit {

    @ManyToOne
    @JoinColumn(name = "institute_id")
    private Institute institute;


    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Employee owner;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    @OneToMany(mappedBy = "chair")
    private List<Course> courseList;

    public Chair(){

    }

    public Chair(String pName, Employee pOwner, Building pBuilding){
        super(pName);
        this.owner = pOwner;
        this.building = pBuilding;
    }

    public Employee getOwner() {
        return owner;
    }

    public void setOwner(Employee owner) {
        this.owner = owner;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }
}
