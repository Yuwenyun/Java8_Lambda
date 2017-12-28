package com.owen.CollectorsAPI;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main
{
	public static void main(String[] args)
	{
		Dish dish1 = new Dish("apple", 300);
		Dish dish2 = new Dish("banana", 250);
		Dish dish3 = new Dish("fish", 540);
		Dish dish4 = new Dish("meat", 890);
		Dish dish5 = new Dish("beef", 1300);
		Dish dish6 = new Dish("potato", 540);
		
		List<Dish> dishes = Arrays.asList(dish1, dish2, dish3, dish4, dish5, dish6);
		
		// count all the dishes
		// dishes.stream().count();
		long count = dishes.stream()
		      .collect(Collectors.counting());
		System.out.println(count + "");
		
		// find the dish with highest calories
		Optional<Dish> max = dishes.stream()
		      // maxBy() is to find the biggest value with a comparator provided by arg
		      .collect(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)));
		max.ifPresent(System.out::println);
		
		// sum all the calories
		int calories = dishes.stream()
		      .collect(Collectors.summingInt(Dish::getCalories));
		System.out.println(calories + "");
		
		// calculate the average calories
		double avgCalories = dishes.stream()
		      .collect(Collectors.averagingInt(Dish::getCalories));
		System.out.println(avgCalories + "");
		
		// summarize
		IntSummaryStatistics summary = dishes.stream()
		      .collect(Collectors.summarizingInt(Dish::getCalories));
		System.out.println(summary.toString());
	}
}
