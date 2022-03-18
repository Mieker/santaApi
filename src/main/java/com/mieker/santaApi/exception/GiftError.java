package com.mieker.santaApi.exception;

public enum GiftError {

    GIFT_NOT_EXIST("Gift doesn't exist");

    private final String message;

    GiftError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
