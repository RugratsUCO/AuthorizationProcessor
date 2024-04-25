package com.authorizationprocessor.authorizationprocessor.utils.exception;

import com.authorizationprocessor.authorizationprocessor.utils.UtilObject;
import com.authorizationprocessor.authorizationprocessor.utils.UtilText;

public abstract class AuthorizationException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private ExceptionType type;
    private String userMessage;

    protected AuthorizationException(String technicalMessage, String userMessage, Throwable rootCause, ExceptionType type) {
        super(technicalMessage, rootCause);
        setUserMessage(userMessage);
        setType(type);
    }

    public final Throwable getRootCause() {
        return UtilObject.getDefault(getCause(), new Exception());
    }

    public final String getTechnicalMessage() {
        return UtilText.getDefaultIfEmpty(getMessage(), getUserMessage());
    }

    public final ExceptionType getType() {
        return type;
    }

    private final void setType(ExceptionType type) {
        this.type = UtilObject.getDefault(type, ExceptionType.GENERAL);
    }

    public final String getUserMessage() {
        return userMessage;
    }

    public final void setUserMessage(String userMessage) {
        this.userMessage = UtilText.getDefault(userMessage);
    }

}
