package com.owen.ParallelStream;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelStreams
{
	public static void main(String[] args)
	{
		System.out.println("Sequential sum is done in " +
	        measureSumPerformance(ParallelStreams::sequentialSum, 1_000_000) + "ms");
		
		// parallel sum costs the most of time because the implementation is not actually
		// parallel and involve a lot of unboxing operation
		System.out.println("Parallel sum is done in " +
	        measureSumPerformance(ParallelStreams::parallelSum, 1_000_000) + "ms");
		
		// this is able to parallel the sum operation since it's using numeric stream api
		System.out.println("Numeric stream sum is done in " +
		    measureSumPerformance(ParallelStreams::parallelRangedSum, 1_000_000) + "ms");
		
		System.out.println("Iterative sum is done in " +
	        measureSumPerformance(ParallelStreams::iterativeSum, 1_000_000) + "ms");
		
		System.out.println("Side effect sum is done in " +
		    measureSumPerformance(ParallelStreams::sideEffectParallelSum, 1_000_000) + "ms");
	}
	
	/**
	 * run the provided func for 10 times and get the fastest time and return the time
	 * @param func
	 * @param n
	 * @return
	 */
	public static long measureSumPerformance(Function<Long, Long> func, long n)
	{
		long fastest = Long.MAX_VALUE;
		for(int i = 0; i < 10; i++)
		{
			long start = System.nanoTime();
			long sum = func.apply(n);
			long duration = (System.nanoTime() - start)/1_000_000;
			System.out.println("Result: " + sum);
			if(duration < fastest)
				fastest = duration;
		}
		return fastest;
	}
	
	public static long sequentialSum(long n)
	{
		// iterate() would generate stream of value: 1L, 2L, 3L, 4L...
		return Stream.iterate(1L, i -> i + 1)
				     .limit(n)
				     .reduce(0L, (a, b) -> a + b);
	}
	
	// iterate() of Stream generates boxed object, which has to be unboxed when
	// perform adding, iterate process is hard to be divided into chunks when parallelizing
	// because the input of next calculation depends on the result of previous calculation.
	public static long parallelSum(long n)
	{
		return Stream.iterate(1L, i -> i + 1)
				     .limit(n)
				     // turn the stream into parallel stream
				     // this won't work as parallel because iterate operation is not able 
				     // to be splitted into chunks.
				     .parallel()
				     .reduce(0L, (a, b) -> a + b);
	}
	
	public static long iterativeSum(long n)
	{
		long result = 0;
		for(int i = 1; i <= n; i++)
		{
			result += i;
		}
		return result;
	}
	
	public static long parallelRangedSum(long n)
	{
		// use the numeric stream api (LongStream) to avoid boxing and unboxing operation
		return LongStream.rangeClosed(1, n)
				         .parallel()
				         .reduce(0, (a, b) -> a + b);
	}
	
	public static long sideEffectParallelSum(long n)
	{
		Accumulator accumulator = new Accumulator(); 
		LongStream.rangeClosed(1, n)
		          .parallel() // there is only one accumulator instance, which
		                      // is accessed by multi-thread here, making the result wrong
		          .forEach(accumulator::add); 
		return accumulator.total;
	}
}
