package com.example.bilabonnmenteksamensprojekt.controllers.api;

import com.example.bilabonnmenteksamensprojekt.models.cars.Car;
import com.example.bilabonnmenteksamensprojekt.models.cars.CarEngine;
import com.example.bilabonnmenteksamensprojekt.models.cars.CarSpecification;
import com.example.bilabonnmenteksamensprojekt.services.AlarmService;
import com.example.bilabonnmenteksamensprojekt.services.cars.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class AlarmsApi {

    private final String version = "v1";
    private final String prefix = "alarms";

    @Autowired
    AlarmService alarmService;

    /*@GetMapping(version + "/" + prefix + "/")
    public List<Car> getAll() {
        //return alarmService.getAlarms();
    }

    @GetMapping(version + "/" + prefix + "/{id}")
    public Car getById(@PathVariable int id) {
        return alarmService.getAlarmById(id);
    }*/

    @PostMapping(version + "/" + prefix + "/")
    public void insert() {
    }

    @PostMapping(version + "/" + prefix + "/{id}")
    public void update(@PathVariable int id) {

    }
}
