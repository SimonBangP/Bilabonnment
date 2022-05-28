package com.example.bilabonnmenteksamensprojekt.services;

public interface SearchableAlarmService {

    int getCount();

    int getCountWithWhereClause(String wherClause);
}
