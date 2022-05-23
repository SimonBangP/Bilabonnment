package com.example.bilabonnmenteksamensprojekt.services;

import com.example.bilabonnmenteksamensprojekt.models.users.User;
import com.example.bilabonnmenteksamensprojekt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationService {

    @Autowired
    UserRepository userRepository;

    public boolean authenticateUser(String username, String password){
        User user = userRepository.getByUsername(username);
        if (user.getUserPassword().equals(password)) {
            return true;
        }
        else {
            return false;
        }
    }
}
