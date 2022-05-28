package com.example.bilabonnmenteksamensprojekt.repositories.alarms;

import com.example.bilabonnmenteksamensprojekt.models.system.alarms.WatchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WatchFilterRepository {

    @Autowired
    JdbcTemplate template;

    public List<WatchFilter> getAll() {
        String sql = "SELECT * FROM watchfilters";
        RowMapper<WatchFilter> rowMapper = new BeanPropertyRowMapper<>(WatchFilter.class);

        return template.query(sql, rowMapper);
    }

    public WatchFilter getWatchFilterById(int id) {
        String sql = "SELECT * FROM watchfilters WHERE WatchFilterId = ? LIMIT 1";
        RowMapper<WatchFilter> rowMapper = new BeanPropertyRowMapper<>(WatchFilter.class);

        List<WatchFilter> filters = template.query(sql, rowMapper, id);

        if (filters.size() <= 0) {
            return null;
        }
        else {
            return filters.get(0);
        }
    }
}
