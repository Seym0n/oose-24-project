package com.sse.ooseproject.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "course")
public class Course {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(mappedBy = "course")
    private List<RoomOccupancy> roomOccupancies;

    @OneToMany(mappedBy = "course")
    private List<Enrollment> enrollmentList;

    @OneToMany(mappedBy = "course")
    private List<TeachingShift> teachingShiftList;

    @ManyToOne
    @JoinColumn(name = "chair_id")
    private Chair chair;

    public Course(){

    }

    public Course(String pName, List<RoomOccupancy> pRoomOccupancies, List<Enrollment> pEnrollmentList, List<TeachingShift> pTeachingShiftList){
        this.name = pName;
        this.roomOccupancies = pRoomOccupancies;
        this.enrollmentList = pEnrollmentList;
        this.teachingShiftList = pTeachingShiftList;
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

    public List<RoomOccupancy> getRoomOccupancies() {
        return roomOccupancies;
    }

    public void setRoomOccupancies(List<RoomOccupancy> roomOccupancies) {
        this.roomOccupancies = roomOccupancies;
    }

    public List<Enrollment> getEnrollmentList() {
        return enrollmentList;
    }

    public void setEnrollmentList(List<Enrollment> enrollmentList) {
        this.enrollmentList = enrollmentList;
    }

    public List<TeachingShift> getTeachingShiftList() {
        return teachingShiftList;
    }

    public void setTeachingShiftList(List<TeachingShift> teachingShiftList) {
        this.teachingShiftList = teachingShiftList;
    }

    public Chair getChair() {
        return chair;
    }

    public void setChair(Chair chair) {
        this.chair = chair;
    }
}
