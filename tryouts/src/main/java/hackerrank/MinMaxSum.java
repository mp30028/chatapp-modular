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

class ResultMinMaxSum {

    /*
     * Complete the 'miniMaxSum' function below.
     *
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static void miniMaxSum(List<Integer> arr) {
    // Write your code here
    	arr.sort(Integer::compareTo);
    	Long minSum = 0L;
    	Long maxSum = 0L;
    	
    	for (int j = 0; (j < 5) && (j < arr.size()-1); j++) {
    		minSum = minSum + arr.get(j);
    		maxSum = maxSum + arr.get((arr.size()-j-1));
    	}
    	
    	System.out.println(minSum + " " + maxSum);
    }

}

public class MinMaxSum {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        ResultMinMaxSum.miniMaxSum(arr);

        bufferedReader.close();
    }
}
