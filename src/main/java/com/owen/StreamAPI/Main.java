package com.owen.StreamAPI;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class Main
{
	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Consumer<String> print = System.out::println;
		
		// Initializing data
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");
		
		List<Transaction> trans = Arrays.asList(
				new Transaction(brian, 2011, 300),
				new Transaction(raoul, 2012, 1000), 
				new Transaction(raoul, 2011, 400),
				new Transaction(mario, 2012, 710),
				new Transaction(mario, 2012, 700),
				new Transaction(alan, 2012, 950));
		
		// Find all transactions in the year 2011 and sort them by value (small to high)
		print.accept("Find all transactions in the year 2011 and sort them by value (small to high)");
		trans.stream()
		     .filter(tran -> tran.getYear() == 2011)
		     .sorted(Comparator.comparing(Transaction::getValue))
		     .forEach(System.out::println);
		print.accept("");
		
		// What are all the unique cities where the traders work
		print.accept("What are all the unique cities where the traders work");
		List<Trader> traders = Arrays.asList(raoul, mario, alan, brian);
		traders.stream()
		       .map(trader -> trader.getCity())
		       .distinct()
		       .forEach(System.out::println);
		print.accept("");
		
		// Find all traders from Cambridge and sort them by name
		print.accept("Find all traders from Cambridge and sort them by name");
		traders.stream()
		       .filter(trader -> "Cambridge".equals(trader.getCity()))
		       .sorted(Comparator.comparing(Trader::getName))
		       .forEach(System.out::println);
		print.accept("");
		
		// Return a string of all traders’ names sorted alphabetically
		print.accept("Return a string of all traders’ names sorted alphabetically");
		String result = traders.stream()
		       .map(trader -> trader.getName())
		       .distinct()
		       .sorted()
		       .reduce("", (a, b) -> a + b);
		print.accept(result);
		print.accept("");
		
		// Are any traders based in Milan
		print.accept("Are any traders based in Milan");
		print.accept(traders.stream()
		       .anyMatch(trader -> "Milan".equals(trader.getCity())) + "");
		print.accept("");
		
		// Print all transactions’ values from the traders living in Cambridge
		print.accept("Print all transactions’ values from the traders living in Cambridge");
		trans.stream()
		     .filter(tran -> "Cambridge".equals(tran.getTrader().getCity()))
		     .forEach(tran -> System.out.println(tran.getValue()));
		print.accept("");
		
		// What’s the highest value of all the transactions
		print.accept("What’s the highest value of all the transactions");
		Optional<Integer> max = trans.stream()
		     .map(tran -> tran.getValue())
		     .reduce((a, b) -> a > b ? a: b);
		print.accept(max.orElse(0) + "");
	}
}
