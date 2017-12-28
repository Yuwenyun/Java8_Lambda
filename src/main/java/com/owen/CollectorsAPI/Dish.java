package com.owen.CollectorsAPI;

public class Dish
{
	private String name;
	private int calories;
	public Dish(String name, int calories)
	{
		super();
		this.name = name;
		this.calories = calories;
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
	
	public String toString()
	{
		return this.name + " has calories " + this.calories;
	}
}
