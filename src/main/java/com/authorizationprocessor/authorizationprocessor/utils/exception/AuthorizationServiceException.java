package com.authorizationprocessor.authorizationprocessor.utils.exception;

public class AuthorizationServiceException extends AuthorizationException{
    protected AuthorizationServiceException(String technicalMessage, String userMessage, Throwable rootCause) {
        super(technicalMessage, userMessage, rootCause, ExceptionType.SERVICE);
    }
    private static final long serialVersionUID = -4636066115510646740L;

    public static AuthorizationServiceException create(final String technicalMessage, final String userMessage, final Throwable rootCause) {
        return new AuthorizationServiceException(technicalMessage, userMessage, rootCause);
    }

    public static AuthorizationServiceException create(final String userMessage) {
        return new AuthorizationServiceException(userMessage, userMessage, new Exception());
    }

    public static AuthorizationServiceException create(final String technicalMessage, final String userMessage) {
        return new AuthorizationServiceException(technicalMessage, userMessage, new Exception());
    }
}
