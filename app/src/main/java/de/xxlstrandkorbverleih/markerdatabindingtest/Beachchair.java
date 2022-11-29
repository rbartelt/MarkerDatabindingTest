package de.xxlstrandkorbverleih.markerdatabindingtest;

import com.google.android.gms.maps.model.LatLng;

public class Beachchair {

    private int number;
    private String type;
    private LatLng location;
    private boolean free;

    public Beachchair(int number,String type, LatLng location, boolean free) {
        this.number=number;
        this.type=type;
        this.location=location;
        this.free = free;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }
}
