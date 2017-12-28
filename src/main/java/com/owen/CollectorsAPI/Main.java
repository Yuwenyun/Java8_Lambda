package com.owen.CollectorsAPI;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main
{
	public static void main(String[] args)
	{
		Dish dish1 = new Dish("apple", 300, Type.Fruit);
		Dish dish2 = new Dish("banana", 250, Type.Fruit);
		Dish dish3 = new Dish("fish", 540, Type.Meat);
		Dish dish4 = new Dish("meat", 890, Type.Meat);
		Dish dish5 = new Dish("beef", 1300, Type.Meat);
		Dish dish6 = new Dish("potato", 540, Type.Other);
		
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
		// dishes.stream().map(dish -> dish.getCalories()).reduce(Integer::sum).get();
		// dishes.stream().mapToInt(dish -> dish.getCalories()).sum();
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
		
		// list all the dishes' name
		String names = dishes.stream()
		      .map(dish -> dish.getName())
		      .collect(Collectors.joining(","));
		System.out.println(names);
		
		// group all the dishes with its type
		Map<Type, List<Dish>> maps = dishes.stream()
		      .collect(Collectors.groupingBy(Dish::getType));
		System.out.println(maps.toString());
		
		// group all the dishes with its type and caloric level
		Map<Type, Map<CaloricLevel, List<Dish>>> multiLvlMap = dishes.stream()
		      .collect(Collectors.groupingBy(Dish::getType,
		    		   Collectors.groupingBy(dish -> {
		    			   if(dish.getCalories() <= 400) return CaloricLevel.Diet;
		    			   else if(dish.getCalories() <= 700) return CaloricLevel.Normal;
		    			   else return CaloricLevel.Fat;
		    		   })));
		System.out.println(multiLvlMap.toString());
		
		// type of collector for the second arg of groupingBy() could be any type
		Map<Type, Long> countMap = dishes.stream()
		      .collect(Collectors.groupingBy(Dish::getType,
		    		   Collectors.counting())); // count for each type
		System.out.println(countMap.toString());
		Map<Type, Optional<Dish>> maxMap = dishes.stream()
		      .collect(Collectors.groupingBy(Dish::getType,
		    		   // max calories for each type
		    		   Collectors.maxBy(Comparator.comparing(Dish::getCalories))));
		System.out.println(maxMap.toString());
		
		// if we don't want the wrap of Optionals
		Map<Type, Dish> maxDishMap = dishes.stream()
		      .collect(Collectors.groupingBy(Dish::getType,
		    		   Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Dish::getCalories)),
		    				   // return type of maxBy is Optional, apply get() for the result
		    				   Optional::get)));
		System.out.println(maxDishMap.toString());
	}
}
