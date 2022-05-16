package com.example.bilabonnmenteksamensprojekt.models.system.alarms;

public class Filter {

    private int filterId;
    private WatchValue watchValue;
    private String filterElement;

    public Filter(int filterId, WatchValue watchValue, String filterElement) {
        this.filterId = filterId;
        this.watchValue = watchValue;
        this.filterElement = filterElement;
    }

    public Filter(WatchValue watchValue, String filterElement) {
        this.watchValue = watchValue;
        this.filterElement = filterElement;
    }

    public int getFilterId() {
        return filterId;
    }

    public WatchValue getWatchValue() {
        return watchValue;
    }

    public void setWatchValue(WatchValue watchValue) {
        this.watchValue = watchValue;
    }

    public String getFilterElement() {
        return filterElement;
    }

    public void setFilterElement(String filterElement) {
        this.filterElement = filterElement;
    }
}
