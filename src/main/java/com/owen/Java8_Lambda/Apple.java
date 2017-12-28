package com.owen.Java8_Lambda;

public class Apple
{
	private String color;
	private Integer weight;
	
	public Apple(){}
	
	public Apple(String color)
	{
		this.color = color;
	}
	public Apple(String color, Integer weight)
	{
		this.color = color;
		this.weight = weight;
	}
	public String toString()
	{
		return this.color + " apple weight " + this.weight;
	}
	public void print(String s)
	{
		System.out.println(s);
	}
}
