package com.sse.ooseproject.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "room_occupancy")
public class RoomOccupancy {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    private LocalDateTime occupancyTime;

    public RoomOccupancy(){

    }

    public RoomOccupancy(Course pCourse, Room pRoom, LocalDateTime pOccupancyTime){
        this.course = pCourse;
        this.room = pRoom;
        this.occupancyTime = pOccupancyTime;
    }

    // Getter & Setter methods

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDateTime getOccupancyTime() {
        return occupancyTime;
    }

    public void setOccupancyTime(LocalDateTime occupancyTime) {
        this.occupancyTime = occupancyTime;
    }
}
