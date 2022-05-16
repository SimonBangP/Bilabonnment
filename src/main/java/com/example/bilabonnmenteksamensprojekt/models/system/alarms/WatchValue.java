package com.example.bilabonnmenteksamensprojekt.models.system.alarms;

public class WatchValue {

    private int watchId;
    private String watchGroup;
    private String watchValue;

    public WatchValue(int watchId, String watchGroup, String watchValue) {
        this.watchId = watchId;
        this.watchGroup = watchGroup;
        this.watchValue = watchValue;
    }

    public WatchValue(String watchGroup, String watchValue) {
        this.watchGroup = watchGroup;
        this.watchValue = watchValue;
    }

    public int getWatchId() {
        return watchId;
    }

    public String getWatchGroup() {
        return watchGroup;
    }

    public void setWatchGroup(String watchGroup) {
        this.watchGroup = watchGroup;
    }

    public String getWatchValue() {
        return watchValue;
    }

    public void setWatchValue(String watchValue) {
        this.watchValue = watchValue;
    }
}
