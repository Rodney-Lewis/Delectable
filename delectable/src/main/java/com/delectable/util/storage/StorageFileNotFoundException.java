package com.delectable.util.storage;

public class StorageFileNotFoundException extends StorageException {

	/**
     *
     */
    private static final long serialVersionUID = 7438317167388573457L;

    public StorageFileNotFoundException(String message) {
		super(message);
	}

	public StorageFileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
