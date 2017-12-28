package com.owen.CollectorsAPI;

public class Dish
{
	private String name;
	private int calories;
	private Type type;
	
	public Dish(String name, int calories, Type type)
	{
		super();
		this.name = name;
		this.calories = calories;
		this.type = type;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getCalories()
	{
		return calories;
	}
	public void setCalories(int calories)
	{
		this.calories = calories;
	}
	public Type getType()
	{
		return this.type;
	}
	
	public String toString()
	{
		return this.name + " has calories " + this.calories;
	}
}
