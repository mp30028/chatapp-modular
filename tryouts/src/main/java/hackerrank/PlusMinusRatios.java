package hackerrank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class ResultPlusMinusRatios {

    /*
     * Complete the 'plusMinus' function below.
     *
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

	public static void plusMinus2(List<Integer> arr) {
		// Write your code here
//		Integer[] results = { 0, 0, 0 };
//		arr.stream().flatMap((n) -> {
//			if (n < 0) {
//				results[0] = results[0] + 1;
//			} else if (n > 0) {
//				results[2] = results[2] + 1;
//			} else {
//				results[1] = results[1] + 1;
//			}
//			return Stream.of(results);
//		}).toList();
		
		double totalValues = arr.size();
		double positiveValues = arr.stream().filter((n)-> n>0).count();
		long negativeValues = arr.stream().filter((n)-> n<0).count();
		long zeroValues = arr.stream().filter((n)-> n==0).count();
		
		System.out.println(String.format("%.6f", totalValues));
		System.out.println(String.format("%.6f", positiveValues));
		System.out.println(String.format("%.6f", positiveValues/totalValues));
		System.out.println(arr.stream().filter((n)-> n==0).count());
		System.out.println(arr.stream().filter((n)-> n>0).count());
	}

	public static void plusMinus1(List<Integer> arr) {
		// Write your code here
		double totalValues = arr.size();
		double[] results = { 0, 0, 0 };
		arr.forEach((n) -> {
			if (n < 0) {
				results[0] = results[0] + 1;
			} else if (n > 0) {
				results[2] = results[2] + 1;
			} else {
				results[1] = results[1] + 1;
			}
		});
		System.out.println(String.format("%.6f", (results[0] / totalValues)));
		System.out.println(String.format("%.6f", (results[1] / totalValues)));
		System.out.println(String.format("%.6f", (results[2] / totalValues)));
	}
	
	public static void plusMinus(List<Integer> arr) {
		// Write your code here
		double[] results = { 0, 0, 0 };
		results[0]= Double.valueOf(arr.stream().filter( (n) -> n > 0 ).count()) / Double.valueOf(arr.size());
		results[1]= Double.valueOf(arr.stream().filter( (n) -> n < 0 ).count()) / Double.valueOf(arr.size());
		results[2]= Double.valueOf(arr.stream().filter( (n) -> n == 0 ).count()) / Double.valueOf(arr.size());
		System.out.println(String.format("%.6f", (results[0])));
		System.out.println(String.format("%.6f", (results[1])));
		System.out.println(String.format("%.6f", (results[2])));
	}
}

public class PlusMinusRatios {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

//        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        ResultPlusMinusRatios.plusMinus(arr);

        bufferedReader.close();
    }
}
