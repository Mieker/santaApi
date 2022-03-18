package com.mieker.santaApi.exception;

public enum UserError {

    GIFT_ALREADY_INCLUDED("That gift is already contained"),
    GIFT_NOT_EXIST("That gift doesn't exist"),
    USER_NOT_EXIST("User doesn't exist");

    private final String message;

    UserError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
