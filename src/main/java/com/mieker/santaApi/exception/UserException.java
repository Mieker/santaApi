package com.mieker.santaApi.exception;

public class UserException extends RuntimeException {

    private final UserError userError;

    public UserException(UserError userError) {
        this.userError = userError;
    }

    public UserError getUserError() {
        return userError;
    }

}
