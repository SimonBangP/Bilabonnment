package com.example.bilabonnmenteksamensprojekt.services;

import com.example.bilabonnmenteksamensprojekt.models.system.Ticket;
import com.example.bilabonnmenteksamensprojekt.models.system.alarms.Alarm;
import com.example.bilabonnmenteksamensprojekt.repositories.alarms.AlarmRepository;
import com.example.bilabonnmenteksamensprojekt.services.cars.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class AlarmService {

    Logger logger = LoggerFactory.getLogger(AlarmService.class);

    @Autowired
    AlarmRepository repository;

    @Autowired
    CarService carService;

    @Autowired
    BookingService bookingService;

    @Autowired
    CustomerService customerService;

    @Autowired
    TicketService ticketService;

    @Autowired
    UserAuthenticationService userService;

    public final int ALARM_DURATION = 60000;

    public AlarmService() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                checkAlarms();
            }
        }, 10000, ALARM_DURATION);
    }

    private void checkAlarms() {
        logger.info("Tjekker alle alarmer");
        List<Alarm> alarms = repository.getAll();

        for (Alarm alarm : alarms) {
            if (checkAlarm(alarm)) {
                logger.debug("Alarm " + alarm.getAlarmId() + " blev ikke valideret opretter ticket");
                createAlarmTicket(alarm);
            }
            else {
                logger.debug("Alarm " + alarm.getAlarmId() + " blev valideret");
            }
        }
    }

    private boolean checkAlarm(Alarm alarm) {
        boolean success = false;
        switch (alarm.getWatchCategory()) {
            case Biler:
                success = getCountResult(carService, alarm);
                break;
            case Bookinger:
                success = getCountResult(bookingService, alarm);
                break;
            case Kunder:
                success = getCountResult(customerService, alarm);
                break;
            case Sager:
                success = getCountResult(ticketService, alarm);
                break;
            case Brugere:
                success = getCountResult(userService, alarm);
                break;
        }

        return success;
    }

    private boolean getCountResult(SearchableAlarmService service, Alarm alarm) {
        String whereClause = parseWhereClause(alarm);

        logger.debug("Tjekker alarm " + alarm.getAlarmId() + ": " + whereClause);

        boolean result = false;
        if (whereClause.trim().equals("")) {
            switch (alarm.getWatchOperator()) {
                case "<":
                    result = (service.getCount() < alarm.getWatchValue());
                    break;
                case ">":
                    result = (service.getCount() > alarm.getWatchValue());
                    break;
                default:
                    result = (service.getCount() == alarm.getWatchValue());
                    break;
            }
        }
        else {
            switch (alarm.getWatchOperator()) {
                case "<":
                    result = (service.getCountWithWhereClause(whereClause) < alarm.getWatchValue());
                    break;
                case ">":
                    result = (service.getCountWithWhereClause(whereClause) > alarm.getWatchValue());
                    break;
                default:
                    result = (service.getCountWithWhereClause(whereClause) == alarm.getWatchValue());
                    break;
            }
        }
        return result;
    }

    private String parseWhereClause(Alarm alarm) {
        String column = alarm.getWatchFilter().getFilterColumn();
        String operator = alarm.getWatchFilter().getFilterOperator();
        String value = alarm.getWatchFilter().getFilterValue();

        return column + " " + operator + " " + value;
    }

    private void createAlarmTicket(Alarm alarm) {
        Ticket ticket = new Ticket(alarm.getUser(), alarm.getSeverity(), "ALARM: " + alarm.getWatchCategory().name(),
                "En alarm er blevet ramt: " + parseWhereClause(alarm));

        if (!ticketService.ticketExists(ticket)) {
            ticketService.insertTicket(ticket);
        }
    }

}
