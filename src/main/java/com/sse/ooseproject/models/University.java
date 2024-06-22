package com.sse.ooseproject.models;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;

import java.util.List;

@Entity
@Table(name = "university")
public class University {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(mappedBy = "university")
    private List<Student> studentList;

    @OneToMany(mappedBy = "university")
    private List<Employee> employeeList;

    @OneToMany(mappedBy = "university")
    private List<Building> buildingList;

    public University(){

    }

    public University(String pName, List<Student> pStudentList, List<Employee> pEmployeeList, List<Building> pBuildingList){
        this.name = pName;
        this.studentList = pStudentList;
        this.employeeList = pEmployeeList;
        this.buildingList = pBuildingList;
    }

    // Getter & Setter methods

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public void setBuildingList(List<Building> buildingList) {
        this.buildingList = buildingList;
    }

    public List<Building> getBuildingList() {
        return buildingList;
    }
}
