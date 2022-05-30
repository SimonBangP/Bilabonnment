package com.example.bilabonnmenteksamensprojekt.models.system.alarms;

import com.example.bilabonnmenteksamensprojekt.models.system.Severity;
import com.example.bilabonnmenteksamensprojekt.models.users.User;

public class Alarm {

    private int alarmId;
    private WatchCategory watchCategory;
    private String filterClause;
    private int watchValue;
    private String watchOperator;
    private User user;
    private Severity severity;

    public Alarm() {
    }

    public Alarm(int alarmId, WatchCategory watchCategory, String filterClause, int watchValue, String watchOperator, User user, Severity severity) {
        this.alarmId = alarmId;
        this.watchCategory = watchCategory;
        this.filterClause = filterClause;
        this.watchValue = watchValue;
        this.watchOperator = watchOperator;
        this.user = user;
        this.severity = severity;
    }

    public Alarm(WatchCategory watchCategory, String filterClause, int watchValue, String watchOperator, User user, Severity severity) {
        this.watchCategory = watchCategory;
        this.filterClause = filterClause;
        this.watchValue = watchValue;
        this.watchOperator = watchOperator;
        this.user = user;
        this.severity = severity;
    }

    public int getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(int alarmId) {
        this.alarmId = alarmId;
    }

    public WatchCategory getWatchCategory() {
        return watchCategory;
    }

    public void setWatchCategory(WatchCategory watchCategory) {
        this.watchCategory = watchCategory;
    }

    public String getFilterClause() {
        return filterClause;
    }

    public void setFilterClause(String filterClause) {
        this.filterClause = filterClause;
    }

    public int getWatchValue() {
        return watchValue;
    }

    public void setWatchValue(int watchValue) {
        this.watchValue = watchValue;
    }

    public String getWatchOperator() {
        return watchOperator;
    }

    public void setWatchOperator(String watchOperator) {
        this.watchOperator = watchOperator;
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

    public String getAlarmInLaymansTermsToString(){
        return("Hvis " + getWatchCategory().name() + " " + getWatchOperator() + " " + getWatchValue());
    }
}
