package com.example.bilabonnmenteksamensprojekt.models.users;

public class UserRight {

    private int RightsId;
    private String description;

    public UserRight(int rightsId, String description) {
        RightsId = rightsId;
        this.description = description;
    }

    public UserRight(String description) {
        this.description = description;
    }

    public int getRightsId() {
        return RightsId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
