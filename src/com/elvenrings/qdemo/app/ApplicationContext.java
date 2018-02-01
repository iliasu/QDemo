package com.elvenrings.qdemo.app;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext implements Cloneable
{
	private static Map<String, Object> map;
	private static volatile ApplicationContext applicationContext;

	private ApplicationContext()
	{
		if (applicationContext != null)
		{
			throw new RuntimeException("Can't get instance. Please use 'getInstance()'");
		}
	}

	public static ApplicationContext getInstance()
	{
		if (applicationContext == null)
		{
			synchronized (ApplicationContext.class)
			{	//double checked locking
				if (applicationContext == null)
				{
					map = new HashMap<>();
					applicationContext = new ApplicationContext();
				}
			}
		}

		return applicationContext;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException
	{
		if (applicationContext != null)
		{
			throw new CloneNotSupportedException();
		}
		return super.clone();
	}

	public static Object get(String key)
	{
		return map.get(key);
	}

	public static void put(String key, Object value)
	{
		map.put(key, value);
	}
}
