package com.example.bilabonnmenteksamensprojekt.repositories;

import com.example.bilabonnmenteksamensprojekt.models.users.Rights;
import com.example.bilabonnmenteksamensprojekt.models.users.User;
import com.example.bilabonnmenteksamensprojekt.models.users.UserRight;
import com.example.bilabonnmenteksamensprojekt.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RightsRepository {

    @Autowired
    JdbcTemplate template;

    private UserRight mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserRight right = new UserRight(rs.getInt(1), Rights.valueOf(rs.getString(2)));
        return right;
    }

    public List<UserRight> getAllRightsForUserById(int userId) {
        String sql = "SELECT * FROM rights WHERE RightsId IN (SELECT RightsId FROM users_rights WHERE UserId = ?)";

        return template.query(sql, (ResultSet rs, int rowNum) -> {
            return mapRow(rs, rowNum);
        }, userId);
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

        List<UserRight> rights = template.query(sql, (ResultSet rs, int rowNum) -> {
            return mapRow(rs, rowNum);
        }, id);

        if (rights.size() <= 0) {
            return null;
        }
        else {
            return rights.get(0);
        }
    }

    public UserRight getRightByDescription(String description) {
        String sql = "SELECT * FROM rights WHERE RightsDescriptions = ?";
        RowMapper<UserRight> rowMapper = new BeanPropertyRowMapper<>(UserRight.class);

        List<UserRight> rights = template.query(sql, (ResultSet rs, int rowNum) -> {
            return mapRow(rs, rowNum);
        }, description);

        if (rights.size() <= 0) {
            return null;
        }
        else {
            return rights.get(0);
        }
    }
}
