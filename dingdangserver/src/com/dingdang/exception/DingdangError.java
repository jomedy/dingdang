package com.dingdang.exception;

public class DingdangError
{
	String request = "";
	int error_code = -1;
	String error = "";

	public DingdangError(String request)
	{
		this.request = request;
	}

	public DingdangError(String request, int error_code)
	{
		this(request);
		this.error_code = error_code;
		this.error = getCause(error_code);
	}

	private String getCause(int error_code)
	{
		return ErrorCode.getCause(error_code);
	}

	public String getRequest()
	{
		return request;
	}

	public void setRequest(String request)
	{
		this.request = request;
	}

	public int getError_code()
	{
		return error_code;
	}

	public void setError_code(int error_code)
	{
		this.error_code = error_code;
	}

	public String getError()
	{
		return error;
	}

	public void setError(String error)
	{
		this.error = error;
	}
}