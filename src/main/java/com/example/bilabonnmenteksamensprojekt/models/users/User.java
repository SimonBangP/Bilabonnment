package com.example.bilabonnmenteksamensprojekt.models.users;

import com.example.bilabonnmenteksamensprojekt.models.locations.Location;

import java.io.Serializable;

public class User implements Serializable {

    private int userId;
    private String firstName;
    private String lastName;
    private Location location;
    private String username;
    private String userPassword;
    private UserRight[] rights;

    public User() {

    }

    public User(int userId, String firstName, String lastName, Location location, String username, String userPassword, UserRight[] rights) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.username = username;
        this.userPassword = userPassword;
        this.rights = rights;
    }

    public User(String firstName, String lastName, Location location, String username, String userPassword, UserRight[] rights) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.username = username;
        this.userPassword = userPassword;
        this.rights = rights;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public UserRight[] getRights() {
        return rights;
    }

    public void setRights(UserRight[] rights) {
        this.rights = rights;
    }
}
