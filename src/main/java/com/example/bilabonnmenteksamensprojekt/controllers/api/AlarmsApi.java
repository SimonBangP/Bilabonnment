package com.example.bilabonnmenteksamensprojekt.controllers.api;

import com.example.bilabonnmenteksamensprojekt.models.system.Severity;
import com.example.bilabonnmenteksamensprojekt.models.system.alarms.Alarm;
import com.example.bilabonnmenteksamensprojekt.models.system.alarms.WatchCategory;
import com.example.bilabonnmenteksamensprojekt.models.users.User;
import com.example.bilabonnmenteksamensprojekt.services.AlarmService;
import com.example.bilabonnmenteksamensprojekt.services.users.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/alarms")
public class AlarmsApi {

    @Autowired
    AlarmService alarmService;

    @Autowired
    UserService userService;

    @Operation(summary = "Gets all alarms", responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "404", content = @Content())})
    @GetMapping("/")
    public ResponseEntity<List<Alarm>> getAll() {
        List<Alarm> alarms = alarmService.getAllAlarms();
        if (alarms == null || alarms.size() <= 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(alarms, HttpStatus.OK);
        }
    }

    @Operation(summary = "Gets a specific alarm", responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "404", content = @Content())})
    @GetMapping("/{id}")
    public ResponseEntity<Alarm> getById(@PathVariable int id) {
        Alarm alarm = alarmService.getAlarmById(id);
        if (alarm == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(alarm, HttpStatus.OK);
        }
    }

    @Operation(summary = "Inserts an alarm", responses = {@ApiResponse(responseCode = "201"), @ApiResponse(responseCode = "400")})
    @PostMapping("/")
    public ResponseEntity<Void> insert(@RequestParam(name = "Category")WatchCategory category, @RequestParam(name = "FilterClause", required = false)String filterClause,
                                 @RequestParam(name = "Value")int value, @RequestParam(name = "Operator")String operator, @RequestParam(name = "UserId")int userid,
                                 @RequestParam(name = "Severity")Severity severity) {
        User user = userService.getUserById(userid);

        if (user == null) {
            return new ResponseEntity("User with userid: " + userid + " not found", HttpStatus.BAD_REQUEST);
        }

        Alarm alarm = new Alarm(category, filterClause, value, operator, user, severity);

        alarmService.insertAlarm(alarm);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Operation(summary = "Updates an alarm", responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "400")})
    @PostMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestParam(name = "Category")WatchCategory category,
                       @RequestParam(name = "FilterClause", required = false)String filterClause,
                       @RequestParam(name = "Value")int value, @RequestParam(name = "Operator")String operator, @RequestParam(name = "UserId")int userid,
                       @RequestParam(name = "Severity")Severity severity) {

        User user = userService.getUserById(userid);

        if (user == null) {
            return new ResponseEntity("User with userid: " + userid + " not found", HttpStatus.BAD_REQUEST);
        }

        Alarm alarm = new Alarm(category, filterClause, value, operator, user, severity);

        alarmService.updateAlarm(id, alarm);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "Removes an alarm", responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "404")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {

        Alarm alarm = alarmService.getAlarmById(id);

        if (alarm == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        alarmService.removeAlarm(alarm);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
