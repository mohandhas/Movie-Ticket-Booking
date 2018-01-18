package com.mtr.customizedexceptions;

public class CustomizedInternalInternalServerError extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomizedInternalInternalServerError(String s)
	{
		super(s);
	}
}