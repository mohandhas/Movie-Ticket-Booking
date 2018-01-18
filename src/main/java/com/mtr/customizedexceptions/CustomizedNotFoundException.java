package com.mtr.customizedexceptions;

public class CustomizedNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomizedNotFoundException (String s)
	{
		super(s);
	}
}
