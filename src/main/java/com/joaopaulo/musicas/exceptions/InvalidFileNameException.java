package com.joaopaulo.musicas.exceptions;

public class InvalidFileNameException extends FileStorageException {
    public InvalidFileNameException(String message) {
        super(message);
    }
}
