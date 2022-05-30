package com.example.bilabonnmenteksamensprojekt.models.users;

import java.io.Serializable;

public class UserRight implements Serializable {

    private int RightsId;
    private Rights description;

    public UserRight() {
    }

    public UserRight(int rightsId, Rights description) {
        RightsId = rightsId;
        this.description = description;
    }

    public UserRight(Rights description) {
        this.description = description;
    }

    public void setRightsId(int rightsId) {
        RightsId = rightsId;
    }

    public int getRightsId() {
        return RightsId;
    }

    public Rights getDescription() {
        return description;
    }

    public void setDescription(Rights description) {
        this.description = description;
    }
}
