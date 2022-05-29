package com.example.bilabonnmenteksamensprojekt.repositories;

import com.example.bilabonnmenteksamensprojekt.models.users.User;
import com.example.bilabonnmenteksamensprojekt.models.users.UserRight;
import com.example.bilabonnmenteksamensprojekt.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RightsRepository {

    @Autowired
    JdbcTemplate template;

    public List<UserRight> getAllRightsForUserById(int userId) {
        String sql = "SELECT * FROM rights WHERE RightsId IN (SELECT RightsId FROM users_rights WHERE UserId = ?)";
        RowMapper<UserRight> rowMapper = new BeanPropertyRowMapper<>(UserRight.class);

        return template.query(sql, rowMapper, userId);
    }

    public void updateRightsForUser(User user) {
        String sql = "DELETE FROM users_rights WHERE UserId = ?";

        template.update(sql, user.getUserId());

        sql = "INSERT INTO users_rights VALUES (?, ?)";
        for (UserRight right : user.getRights()) {
            template.update(sql, right.getRightsId(), user.getUserId());
        }
    }

    public UserRight getRightById(int id) {
        String sql = "SELECT * FROM rights WHERE RightsId = ?";
        RowMapper<UserRight> rowMapper = new BeanPropertyRowMapper<>(UserRight.class);

        List<UserRight> rights = template.query(sql, rowMapper, id);

        if (rights.size() <= 0) {
            return null;
        }
        else {
            return rights.get(0);
        }
    }

    public UserRight getRightByDescription(String description) {
        String sql = "SELECT * FROM rights WHERE RightsDescription = ?";
        RowMapper<UserRight> rowMapper = new BeanPropertyRowMapper<>(UserRight.class);

        List<UserRight> rights = template.query(sql, rowMapper, description);

        if (rights.size() <= 0) {
            return null;
        }
        else {
            return rights.get(0);
        }
    }
}
