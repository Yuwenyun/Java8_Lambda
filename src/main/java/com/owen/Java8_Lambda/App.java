package com.owen.Java8_Lambda;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
//    	String employee = "Owen";
//    	int count = 0;
//        print((String name) -> System.out.println("Hello " + name), employee);
//        print((Integer i) -> System.out.println(++i), count);
    	
    	Function<String, Apple> func1 = Apple::new;
    	Apple apple = func1.apply("Owen");
    	System.out.println(apple.toString());
    	Predicate<Apple> predicate = (Apple a) -> a.toString() != null;
    	Consumer<String> c = apple::print;
    	c.accept("Owen");
    }
    
    public static <T> void print(MyFunctionalInterface<T> func, T name)
    {
    	func.process(name);
    }
}
