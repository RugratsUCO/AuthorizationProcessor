package com.authorizationprocessor.authorizationprocessor.utils;

public interface MessageSender<T> {
    void execute(T message, String idMessage);
}