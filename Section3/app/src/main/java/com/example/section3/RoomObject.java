package com.example.section3;

public class RoomObject {
    private int roomId;
    private String roomName;
    private String floorId;

    public RoomObject(int i, String room1, String s) {
        this.roomId = i;
        this.roomName = room1;
        this.floorId = s;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    @Override
    public String toString() {
        return  roomName;
    }
}
