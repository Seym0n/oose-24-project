package com.sse.ooseproject.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "building")
public class Building {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(mappedBy = "building")
    private List<Room> roomList;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    public Building(){
    }

    public Building(String pName, List<Room> pRoomList, University pUniversity){
        this.name = pName;
        this.roomList = pRoomList;
        this.university = pUniversity;
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

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }
}
