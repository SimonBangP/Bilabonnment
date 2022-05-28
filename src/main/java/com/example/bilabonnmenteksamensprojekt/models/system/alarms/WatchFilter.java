package com.example.bilabonnmenteksamensprojekt.models.system.alarms;

public class WatchFilter {

    private int filterId;
    private String filterColumn;
    private String filterOperator;
    private String filterValue;

    public WatchFilter() {
    }

    public WatchFilter(int filterId, String filterColumn, String filterOperator, String filterValue) {
        this.filterId = filterId;
        this.filterColumn = filterColumn;
        this.filterOperator = filterOperator;
        this.filterValue = filterValue;
    }

    public WatchFilter(String filterColumn, String filterOperator, String filterValue) {
        this.filterColumn = filterColumn;
        this.filterOperator = filterOperator;
        this.filterValue = filterValue;
    }

    public int getFilterId() {
        return filterId;
    }

    public void setFilterId(int filterId) {
        this.filterId = filterId;
    }

    public String getFilterColumn() {
        return filterColumn;
    }

    public void setFilterColumn(String filterColumn) {
        this.filterColumn = filterColumn;
    }

    public String getFilterOperator() {
        return filterOperator;
    }

    public void setFilterOperator(String filterOperator) {
        this.filterOperator = filterOperator;
    }

    public String getFilterValue() {
        return filterValue;
    }

    public void setFilterValue(String filterValue) {
        this.filterValue = filterValue;
    }
}
