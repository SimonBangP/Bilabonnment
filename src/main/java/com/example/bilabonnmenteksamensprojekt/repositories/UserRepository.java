package com.example.bilabonnmenteksamensprojekt.repositories;

import com.example.bilabonnmenteksamensprojekt.models.users.Rights;
import com.example.bilabonnmenteksamensprojekt.models.users.User;
import com.example.bilabonnmenteksamensprojekt.models.users.UserRight;
import com.example.bilabonnmenteksamensprojekt.services.LocationsService;
import com.example.bilabonnmenteksamensprojekt.services.users.RightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate template;

    @Autowired
    LocationsService locationsService;

    @Autowired
    RightsService rightsService;

    private User mapRow(RowMapper<User> rowMapper, ResultSet rs, int rowNum) throws SQLException {
        User foundUser = rowMapper.mapRow(rs, rowNum);

        foundUser.setLocation(locationsService.getLocationById(rs.getInt(4)));
        foundUser.setRights(rightsService.getAllRightsForUserById(1).toArray(new UserRight[] {}));

        return foundUser;
    }

    public User getByUsername(String username) {
        String sql = "SELECT * FROM users WHERE Username = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);

        List<User> users = template.query(sql, (ResultSet rs, int rowNum) -> {
            return mapRow(rowMapper, rs, rowNum);
        }, username);

        if (users.size() <= 0) {
            return null;
        }
        else {
            return users.get(0);
        }
    }

    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE UserId = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);

        List<User> users = template.query(sql, (ResultSet rs, int rowNum) -> {
            return mapRow(rowMapper, rs, rowNum);
        }, id);

        if (users.size() <= 0) {
            return null;
        }
        else {
            return users.get(0);
        }
    }

    public int getUserId(String username) {
        String sql = "SELECT UserId FROM users WHERE username = ?";

        List<Integer> ids = template.query(sql, (ResultSet rs, int rowNum) -> {
            return rs.getInt(1);
        }, username);

        if (ids.size() <= 0) {
            return -1;
        }
        else {
            return ids.get(0);
        }
    }

    public List<Rights> getUserRights(String username) {
        String sql = "SELECT RightsDescriptions FROM rights WHERE RightsId IN (SELECT RightsId FROM users_rights WHERE UserId = ?)";

        int userId = getUserId(username);

        return template.query(sql, (ResultSet rs, int rowNum) -> {
            return Rights.valueOf(rs.getString(1));
        }, userId);
    }

    public int getCount() {
        String sql = "SELECT COUNT(UserId) FROM users";

        return template.queryForObject(sql, Integer.class);
    }

    public int getCountWithWhereClause(String whereClause) {
        String sql = "SELECT COUNT(UserId) FROM users WHERE " + whereClause;

        return template.queryForObject(sql, Integer.class);
    }

    public List<User> getUsers() {
        String sql = "SELECT * FROM users";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);

        return template.query(sql, (ResultSet rs, int rowNum) -> {
            return mapRow(rowMapper, rs, rowNum);
        });
    }

    public void insertUser(User user) {
        String sql = "INSERT INTO users VALUES (DEFAULT, ?, ?, ?, ?, ?)";

        template.update(sql, user.getFirstName(), user.getLastName(), user.getLocation().getLocationId(), user.getUsername(), user.getUserPassword());
        user.setUserId(getUserId(user.getUsername()));
        rightsService.updateRightsForUser(user);
    }

    public void updateUser(int id, User user) {
        String sql = "UPDATE users SET FirstName = ?, LastName = ?, LocationId = ?, Username = ?, Password = ? WHERE UserId = ?";

        template.update(sql, user.getFirstName(), user.getLastName(), user.getLocation().getLocationId(), user.getUsername(), user.getUserPassword(), id);
        rightsService.updateRightsForUser(user);
    }

    public void removeUserById(int userId) {
        String sql = "DELETE FROM users WHERE UserId = ?";

        template.update(sql, userId);
    }

    public boolean userExists(User user){
        String sql = "SELECT COUNT(userId)";
        return false;
    }
}
