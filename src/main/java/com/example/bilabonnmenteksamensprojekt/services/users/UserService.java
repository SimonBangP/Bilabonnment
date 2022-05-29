package com.example.bilabonnmenteksamensprojekt.services.users;

import com.example.bilabonnmenteksamensprojekt.models.users.User;
import com.example.bilabonnmenteksamensprojekt.repositories.UserRepository;
import com.example.bilabonnmenteksamensprojekt.services.SearchableAlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements SearchableAlarmService {

    @Autowired
    UserRepository repository;

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
        repository.insertUser(user);
    }

    public void updateUser(int id, User user) {
        repository.updateUser(id, user);
    }

    public void removeUser(User user) {
        repository.removeUserById(user.getUserId());
    }
}
