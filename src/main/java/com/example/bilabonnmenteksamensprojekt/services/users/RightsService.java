package com.example.bilabonnmenteksamensprojekt.services.users;

import com.example.bilabonnmenteksamensprojekt.models.users.Rights;
import com.example.bilabonnmenteksamensprojekt.models.users.User;
import com.example.bilabonnmenteksamensprojekt.models.users.UserRight;
import com.example.bilabonnmenteksamensprojekt.repositories.RightsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<UserRight> getRightsByDescriptions(Rights[] rights) {
        List<UserRight> userRights = new ArrayList<>();

        for (Rights right : rights) {
            userRights.add(getRightByDescription(right.name()));
        }

        return userRights;
    }
}
