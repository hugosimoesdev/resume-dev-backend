package dev.resume.backend.exceptions;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException() {
        super("User already exist");
    }
}
