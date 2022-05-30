package com.example.bilabonnmenteksamensprojekt.services.users;

import com.example.bilabonnmenteksamensprojekt.models.users.Rights;
import com.example.bilabonnmenteksamensprojekt.models.users.User;
import com.example.bilabonnmenteksamensprojekt.models.users.UserRight;
import com.example.bilabonnmenteksamensprojekt.repositories.UserRepository;
import com.example.bilabonnmenteksamensprojekt.services.SearchableAlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements SearchableAlarmService {

    @Autowired
    UserRepository repository;

    @Autowired
    RightsService rightsService;

    public boolean authenticateUser(String username, String password){
        User user = repository.getByUsername(username);
        if (user != null && user.getUserPassword().equals(password)) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean userHasRight(String username, String right) {
        List<String> userRights = repository.getUserRights(username);

        for (String r : userRights) {
            if (r.equalsIgnoreCase("administrator")) {
                return true;
            }
            if (r.equalsIgnoreCase(right)) {
                return true;
            }
        }

        return false;
    }

    public User getUserByUsername(String username) {
        return repository.getByUsername(username);
    }

    public User getUserById(int id) {
        return repository.getUserById(id);
    }

    @Override
    public int getCount() {
        return repository.getCount();
    }

    @Override
    public int getCountWithWhereClause(String whereClause) {
        return repository.getCountWithWhereClause(whereClause);
    }

    public List<User> getUsers() {
        return repository.getUsers();
    }

    public void insertUser(User user) {
        if (repository.getByUsername(user.getUsername()) == null) {
            repository.insertUser(user);
        }
    }

    public void updateUser(int id, User user) {
        user.setUserId(id);
        repository.updateUser(id, user);
        rightsService.updateRightsForUser(user);
    }

    public void removeUser(User user) {
        user.setRights(new UserRight[0]);
        rightsService.updateRightsForUser(user);
        repository.removeUserById(user.getUserId());
    }
}
