package com.mtr.exceptionadvice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mtr.customizedexceptions.CustomizedBadRequestException;
import com.mtr.customizedexceptions.CustomizedFormatException;
import com.mtr.customizedexceptions.CustomizedNotFoundException;

@RestControllerAdvice
public class ExceptionAdvice {

	@ExceptionHandler(CustomizedNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String notFoundException(CustomizedNotFoundException e)
	{
		return e+"";
	}
	
	@ExceptionHandler(CustomizedBadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String badRequestException(CustomizedBadRequestException e)
	{
		return e+"";
	}
	
	@ExceptionHandler(CustomizedFormatException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public String formatException(CustomizedFormatException e)
	{
		return e+"";
	}
	
	@ExceptionHandler(CustomizedBadRequestException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public String internalServerErrorException(CustomizedBadRequestException e)
	{
		return e+"";
	}
	
}
