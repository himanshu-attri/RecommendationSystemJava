package org.recommendation.model;

public class BaseModel {
    private String userId;

    public String getUserId() {
        return userId;
    }

    BaseModel(final String userId) {
        this.userId = userId;
    }

}
