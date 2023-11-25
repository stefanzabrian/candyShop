package com.candyShop.rest.model.constant;

public enum UserRole {
    OWNER("Owner"),
    CLIENT("Client");
    private final String displayName;

    UserRole(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
