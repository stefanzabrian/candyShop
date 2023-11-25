package com.candyShop.rest.model.constant;

public enum OrderStatus {
    PLACED("Placed"),
    ON_PROGRESS("On Progress"),
    DELIVERED("Delivered");
    private final String displayName;

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
