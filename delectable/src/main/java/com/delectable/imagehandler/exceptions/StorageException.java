package com.delectable.imagehandler.exceptions;

public class StorageException extends RuntimeException {

    private static final long serialVersionUID = 7578376001605359601L;

    public StorageException(String message) {
		super(message);
	}

	public StorageException(String message, Throwable cause) {
		super(message, cause);
	}
}
