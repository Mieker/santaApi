package com.mieker.santaApi.exception;

public class GiftException extends RuntimeException {

    private final GiftError giftError;

    public GiftException(GiftError giftError) {
        this.giftError = giftError;
    }

    public GiftError getGiftError() {
        return giftError;
    }

}
