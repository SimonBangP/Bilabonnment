package com.example.bilabonnmenteksamensprojekt.models.users;

import com.example.bilabonnmenteksamensprojekt.models.locations.Location;

public class User {

    private int userId;
    private String firstName;
    private String lastName;
    private Location address;
    private String username;
    //private String password   -- Should we have a password here???
    private UserRight[] rights;

    public User(int userId, String firstName, String lastName, Location address, String username, UserRight[] rights) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.username = username;
        this.rights = rights;
    }

    public User(String firstName, String lastName, Location address, String username, UserRight[] rights) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.username = username;
        this.rights = rights;
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

    public Location getAddress() {
        return address;
    }

    public void setAddress(Location address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRight[] getRights() {
        return rights;
    }

    public void setRights(UserRight[] rights) {
        this.rights = rights;
    }
}
