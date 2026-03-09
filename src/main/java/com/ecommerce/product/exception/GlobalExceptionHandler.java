
package com.ecommerce.product.exception;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> handleConstraintViolation(ConstraintViolationException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation error : " + ex.getMessage());
	}

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<?> handleNotFound(ProductNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<?> handleBad(BadRequestException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad request Exception : " + ex.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleBad(MethodArgumentNotValidException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body("The Field value passed are not valid : " + ex.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleAll(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Internal error detail : " + ex.getMessage());
	}

}
