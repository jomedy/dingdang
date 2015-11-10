package com.dingdang.exception;

public class ErrorCode
{
	public static final long serialVersionUID = -176092625883595547L;
	public static final int OK = 200; // OK: Success!
	public static final int NOT_MODIFIED = 304; // Not Modified: There was no
												// new data to return.
	public static final int BAD_REQUEST = 400; // Bad Request: The request was
												// invalid. An accompanying
												// error message will explain
												// why. This is the status code
												// will be returned during rate
												// limiting.
	public static final int NOT_AUTHORIZED = 401; // Not Authorized:
													// Authentication
													// credentials were missing
													// or incorrect.
	public static final int FORBIDDEN = 403; // Forbidden: The request is
												// understood, but it has been
												// refused. An accompanying
												// error message will explain
												// why.
	public static final int NOT_FOUND = 404; // Not Found: The URI requested is
												// invalid or the resource
												// requested, such as a user,
												// does not exists.
	public static final int NOT_ACCEPTABLE = 406; // Not Acceptable: Returned by
													// the Search API when an
													// invalid format is
													// specified in the request.
	public static final int INTERNAL_SERVER_ERROR = 500;// Internal Server
														// Error: Something is
														// broken. Please post
														// to the group so the
														// Weibo team can
														// investigate.
	public static final int BAD_GATEWAY = 502;// Bad Gateway: Weibo is down or
												// being upgraded.
	public static final int SERVICE_UNAVAILABLE = 503;// Service Unavailable:
														// The Weibo servers are
														// up, but overloaded
														// with requests. Try
														// again later. The
														// search and trend
														// methods use this to
														// indicate when you are
														// being rate limited.

	//dq 定义
	public static final int ERROR_ARGS = 20001;
	
	//下面由各位自定义，错误代码开头分别定义使用
	//........
	

	public static String getCause(int statusCode)
	{
		String cause = null;
		switch (statusCode)
		{
		case ERROR_ARGS:
			cause = "参数错误";
			break;
		default:
			cause = "";
		}
		return cause;
	}
}
