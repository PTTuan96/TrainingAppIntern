package com.practice.exception;

public class DuplicateDataException extends Exception {

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateDataException(String message) {
        super(message);
    }

}
