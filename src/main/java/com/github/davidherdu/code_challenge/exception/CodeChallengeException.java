package com.github.davidherdu.code_challenge.exception;

public class CodeChallengeException extends RuntimeException {

	private static final long serialVersionUID = -2708712201491693778L;

	public CodeChallengeException() {
		super();
	}

	public CodeChallengeException(String message, Throwable cause) {
		super(message, cause);
	}

	public CodeChallengeException(String message) {
		super(message);
	}
}
