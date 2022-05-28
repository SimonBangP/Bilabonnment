package com.example.bilabonnmenteksamensprojekt.repositories.alarms;

import com.example.bilabonnmenteksamensprojekt.models.system.alarms.Alarm;
import com.example.bilabonnmenteksamensprojekt.services.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class AlarmRepository {

    @Autowired
    JdbcTemplate template;

    @Autowired
    WatchFilterRepository watchFilterRepository;

    @Autowired
    UserAuthenticationService userService;

    public List<Alarm> getAll() {
        String sql = "SELECT * FROM alarms";
        RowMapper<Alarm> rowMapper = new BeanPropertyRowMapper<>(Alarm.class);

        return template.query(sql, (ResultSet rs, int rowNum) -> {
            Alarm foundAlarm = rowMapper.mapRow(rs, rowNum);

            foundAlarm.setUser(userService.getUserById(rs.getInt(6)));
            foundAlarm.setWatchFilter(watchFilterRepository.getWatchFilterById(rs.getInt(3)));

            return foundAlarm;
        });
    }
}
