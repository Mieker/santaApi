package com.mieker.santaApi.exception;

public class GiftException extends RuntimeException {

    private GiftError giftError;

    public GiftException(GiftError giftError) {
        this.giftError = giftError;
    }

    public GiftError getGiftError() {
        return giftError;
    }

}
