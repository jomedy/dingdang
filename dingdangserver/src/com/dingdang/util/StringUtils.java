package com.dingdang.util;

public class StringUtils
{
	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str)
	{
		if (str == null || str.equals("") || str.length() <= 0)
		{
			return true;
		}
		return false;
	}

	/**
	 * 判断字符串是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isInt(String str)
	{
		if (str == null || str.equals(""))
		{
			return false;
		}
		try
		{
			Integer.valueOf(str);
		}
		catch (NumberFormatException e)
		{
			return false;
		}
		return true;
	}

	/**
	 * 判断是否为正数
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isPositiveInt(String str)
	{
		if (str == null || str.equals(""))
		{
			return false;
		}
		try
		{
			int i = Integer.valueOf(str);
			if (i <= 0)
			{
				return false;
			}
		}
		catch (NumberFormatException e)
		{
			return false;
		}
		return true;
	}
}
