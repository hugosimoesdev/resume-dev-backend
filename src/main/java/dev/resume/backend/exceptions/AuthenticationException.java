package dev.resume.backend.exceptions;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException() {
        super("Provided username or password is incorrect.");
    }
}
