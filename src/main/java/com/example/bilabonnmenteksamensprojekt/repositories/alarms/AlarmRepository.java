package com.example.bilabonnmenteksamensprojekt.repositories.alarms;

import com.example.bilabonnmenteksamensprojekt.models.system.alarms.Alarm;
import com.example.bilabonnmenteksamensprojekt.services.users.UserService;
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
    UserService userService;

    public List<Alarm> getAll() {
        String sql = "SELECT * FROM alarms";
        RowMapper<Alarm> rowMapper = new BeanPropertyRowMapper<>(Alarm.class);

        return template.query(sql, (ResultSet rs, int rowNum) -> {
            Alarm foundAlarm = rowMapper.mapRow(rs, rowNum);

            if (foundAlarm.getFilterClause() == null) {
                foundAlarm.setFilterClause("");
            }
            foundAlarm.setUser(userService.getUserById(rs.getInt(6)));

            return foundAlarm;
        });
    }

    public Alarm getAlarmById(int id) {
        String sql = "SELECT * FROM alarms WHERE AlarmId = ?";
        RowMapper<Alarm> rowMapper = new BeanPropertyRowMapper<>(Alarm.class);

        List<Alarm> alarms = template.query(sql, (ResultSet rs, int rowNum) -> {
            Alarm foundAlarm = rowMapper.mapRow(rs, rowNum);

            if (foundAlarm.getFilterClause() == null) {
                foundAlarm.setFilterClause("");
            }
            foundAlarm.setUser(userService.getUserById(rs.getInt(6)));

            return foundAlarm;
        }, id);

        if (alarms.size() <= 0) {
            return null;
        }
        else {
            return alarms.get(0);
        }
    }

    public void insertAlarm(Alarm alarm) {
        String sql = "INSERT INTO alarms VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)";

        template.update(sql, alarm.getWatchCategory().name(), alarm.getFilterClause(), alarm.getWatchValue(),
                            alarm.getWatchOperator(), alarm.getUser().getUserId(), alarm.getSeverity().name());
    }

    public void updateAlarm(int id, Alarm alarm) {
        String sql = "UPDATE alarms SET WatchCategory = ?, FilterClause = ?, WatchValue = ?, WatchOperator = ?, UserId = ?, Severity = ? WHERE AlarmId = ?";

        template.update(sql, alarm.getWatchCategory().name(), alarm.getFilterClause(), alarm.getWatchValue(),
                alarm.getWatchOperator(), alarm.getUser().getUserId(), alarm.getSeverity().name(), id);
    }

    public void removeAlarmById(int id) {
        String sql = "DELETE FROM alarms WHERE AlarmId = ?";

        template.update(sql, id);
    }
}
