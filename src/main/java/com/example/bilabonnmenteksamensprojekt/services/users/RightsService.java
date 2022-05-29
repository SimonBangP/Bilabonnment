package com.example.bilabonnmenteksamensprojekt.services.users;

import com.example.bilabonnmenteksamensprojekt.models.users.User;
import com.example.bilabonnmenteksamensprojekt.models.users.UserRight;
import com.example.bilabonnmenteksamensprojekt.repositories.RightsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RightsService {

    @Autowired
    RightsRepository repository;

    public List<UserRight> getAllRightsForUserById(int userId) {
        return repository.getAllRightsForUserById(userId);
    }

    public void updateRightsForUser(User user) {
        repository.updateRightsForUser(user);
    }

    public UserRight getRightById(int id) {
        return repository.getRightById(id);
    }

    public UserRight getRightByDescription(String description) {
        return repository.getRightByDescription(description);
    }
}
