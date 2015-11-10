package com.dingdang.category;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.dingdang.category.model.Category;
import com.dingdang.category.service.CategoryService;
import com.dingdang.exception.DingdangError;
import com.dingdang.exception.DingdangException;
import com.dingdang.exception.ErrorCode;
import com.dingdang.util.StringUtils;
import com.google.gson.Gson;

public class GetCategoryServlet extends HttpServlet
{
	private CategoryService categoryService;

	private static final String REQUEST_URI = "getCategory.json";

	@Override
	public void init() throws ServletException
	{
		ApplicationContext actx = WebApplicationContextUtils
				.getWebApplicationContext(this.getServletContext());
		categoryService = (CategoryService) actx.getBean("categoryService");
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		response.setContentType("application/json");
		response.setContentType("text/plain; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");

		Gson gson = new Gson();

		String categoryIdStr = request.getParameter("categoryId");
		String pageNoStr = request.getParameter("pageNo");
		String pageSizeStr = request.getParameter("pageSize");

		String jsonString = "";
		List<Category> list = null;
		try
		{
			if (!StringUtils.isInt(categoryIdStr)
					|| !StringUtils.isPositiveInt(pageNoStr)
					|| !StringUtils.isPositiveInt(pageSizeStr))
			{
				throw new DingdangException(ErrorCode.ERROR_ARGS);
			}

			int categoryId = Integer.valueOf(categoryIdStr);
			int pageNo = Integer.valueOf(pageNoStr);
			int pageSize = Integer.valueOf(pageSizeStr);

			list = categoryService.getCategory(categoryId, pageNo, pageSize);
			gson.toJson(list);
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

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request, response);
	}

}
