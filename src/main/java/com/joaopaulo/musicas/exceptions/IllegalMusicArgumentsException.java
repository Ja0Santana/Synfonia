package com.joaopaulo.musicas.exceptions;

public class IllegalMusicArgumentsException extends RuntimeException {
    public IllegalMusicArgumentsException(String message) {
        super(message);
    }
    public IllegalMusicArgumentsException(String message, Throwable cause) {
        super(message, cause);
    }
}
