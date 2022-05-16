package com.example.bilabonnmenteksamensprojekt.models.system.alarms;

import com.example.bilabonnmenteksamensprojekt.models.system.Severity;
import com.example.bilabonnmenteksamensprojekt.models.users.User;

public class Alarm {

    private int alarmId;
    private WatchValue watchValue;
    private WatchFilter watchFilter;
    private User user;
    private Severity severity;

    public Alarm(int alarmId, WatchValue watchValue, WatchFilter watchFilter, User user, Severity severity) {
        this.alarmId = alarmId;
        this.watchValue = watchValue;
        this.watchFilter = watchFilter;
        this.user = user;
        this.severity = severity;
    }

    public Alarm(WatchValue watchValue, WatchFilter watchFilter, User user, Severity severity) {
        this.watchValue = watchValue;
        this.watchFilter = watchFilter;
        this.user = user;
        this.severity = severity;
    }

    public int getAlarmId() {
        return alarmId;
    }

    public WatchValue getWatchValue() {
        return watchValue;
    }

    public void setWatchValue(WatchValue watchValue) {
        this.watchValue = watchValue;
    }

    public WatchFilter getWatchFilter() {
        return watchFilter;
    }

    public void setWatchFilter(WatchFilter watchFilter) {
        this.watchFilter = watchFilter;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }
}
