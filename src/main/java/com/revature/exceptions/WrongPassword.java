package com.revature.exceptions;

public class WrongPassword extends RuntimeException
{
	public WrongPassword() {};
	
	public WrongPassword(String message)
	{
		super(message);
	}

	public WrongPassword(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public WrongPassword(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public WrongPassword(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
