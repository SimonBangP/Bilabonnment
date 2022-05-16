package com.example.bilabonnmenteksamensprojekt.models.system.alarms;

public class WatchFilter {

    private int filterId;
    private Filter filter;
    private char filterOperator;
    private int filterValue;

    public WatchFilter(int filterId, Filter filter, char filterOperator, int filterValue) {
        this.filterId = filterId;
        this.filter = filter;
        this.filterOperator = filterOperator;
        this.filterValue = filterValue;
    }

    public WatchFilter(Filter filter, char filterOperator, int filterValue) {
        this.filter = filter;
        this.filterOperator = filterOperator;
        this.filterValue = filterValue;
    }

    public int getFilterId() {
        return filterId;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public char getFilterOperator() {
        return filterOperator;
    }

    public void setFilterOperator(char filterOperator) {
        this.filterOperator = filterOperator;
    }

    public int getFilterValue() {
        return filterValue;
    }

    public void setFilterValue(int filterValue) {
        this.filterValue = filterValue;
    }
}
