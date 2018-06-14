package com.github.davidherdu.code_challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.github.davidherdu.code_challenge.beans.ResponseWrapper;
import com.github.davidherdu.code_challenge.constants.Constants;

@ControllerAdvice  
@RestController
public class CodeChallengeExceptionChecker {
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ResponseWrapper> handleRuntimeException(RuntimeException e) {
		e.printStackTrace();
		ResponseWrapper error = new ResponseWrapper(HttpStatus.INTERNAL_SERVER_ERROR.name(), Constants.ERR_GENERIC_DESC, Constants.ERR_ERROR);
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(CodeChallengeException.class)
	public ResponseEntity<ResponseWrapper> handlePacklinkException(CodeChallengeException e) {
		ResponseWrapper error = new ResponseWrapper(HttpStatus.NOT_ACCEPTABLE.name(), e.getMessage(), Constants.ERR_ERROR);
		return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
	}
}
