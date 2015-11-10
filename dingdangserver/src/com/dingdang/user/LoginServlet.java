package com.dingdang.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.dingdang.exception.DingdangError;
import com.dingdang.exception.DingdangException;
import com.dingdang.exception.ErrorCode;
import com.dingdang.user.service.UserService;
import com.dingdang.util.LoggerUtils;
import com.dingdang.util.StringUtils;
import com.google.gson.Gson;

public class LoginServlet extends HttpServlet
{
	private UserService userService;

	private static final String REQUEST_URI = "login.json";

	@Override
	public void init() throws ServletException
	{
		ApplicationContext actx = WebApplicationContextUtils
				.getWebApplicationContext(this.getServletContext());
		userService = (UserService) actx.getBean("userService");
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		response.setContentType("application/json");
		response.setContentType("text/plain; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");

		Gson gson = new Gson();

		String tele = request.getParameter("tele");
		String password = request.getParameter("pwd");

		String jsonString = "";
		int state = 0;
		Map<String, Object> map = new HashMap<String, Object>();

		try
		{
			if (StringUtils.isEmpty(tele) || StringUtils.isEmpty(password))
			{
				throw new DingdangException(ErrorCode.ERROR_ARGS);
			}
			
			state = userService.checkLogin(tele, password);
			
			if(1 == state)
			{
				LoggerUtils.info(request.getRemoteAddr() + "-" + tele + "-login success");
			}
			else
			{
				LoggerUtils.info(request.getRemoteAddr() + "-" + tele + "-login error");
			}
			map.put("tele", tele);
			map.put("state", state);
			jsonString = gson.toJson(map);
		}
		catch (DingdangException de)
		{
			jsonString = gson.toJson(new DingdangError(REQUEST_URI, de
					.getStatusCode()));
			de.printStackTrace();
		}
		catch (Throwable t)
		{
			t.printStackTrace();
		}
		finally
		{
			PrintWriter out = response.getWriter();
			out.print(jsonString);
			out.flush();
			out.close();
		}
	}

}
