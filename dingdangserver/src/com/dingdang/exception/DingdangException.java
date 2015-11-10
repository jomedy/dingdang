package com.dingdang.exception;

public class DingdangException extends Exception
{
	private static final long serialVersionUID = -2623309261327598087L;

	private int statusCode = -1;
	private int errorCode = -1;
	private String request;
	private String error;

	public int getStatusCode()
	{
		return this.statusCode;
	}

	public int getErrorCode()
	{
		return errorCode;
	}

	public String getRequest()
	{
		return request;
	}

	public String getError()
	{
		return error;
	}

	public DingdangException(int statusCode)
	{
		super(ErrorCode.getCause(statusCode));
		this.statusCode = statusCode;
	}

	public DingdangException(String msg)
	{
		super(msg);
	}

	public DingdangException(Exception cause)
	{
		super(cause);
	}

	public DingdangException(String msg, int statusCode)
	{
		super(msg);
		this.statusCode = statusCode;
	}

	public DingdangException(String msg, Exception cause)
	{
		super(msg, cause);
	}

	public DingdangException(String msg, Exception cause, int statusCode)
	{
		super(msg, cause);
		this.statusCode = statusCode;
	}
}
