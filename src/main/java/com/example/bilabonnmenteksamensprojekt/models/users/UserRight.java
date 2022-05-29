package com.example.bilabonnmenteksamensprojekt.models.users;

import java.io.Serializable;

public class UserRight implements Serializable {

    private int RightsId;
    private String description;

    public UserRight() {
    }

    public UserRight(int rightsId, String description) {
        RightsId = rightsId;
        this.description = description;
    }

    public UserRight(String description) {
        this.description = description;
    }

    public void setRightsId(int rightsId) {
        RightsId = rightsId;
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
