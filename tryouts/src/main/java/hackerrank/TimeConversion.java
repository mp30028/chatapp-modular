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

class TimeConversionResult {

    /*
     * Complete the 'timeConversion' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String timeConversion(String s) {
    // Write your code here
    	int hr = Integer.parseInt(s.substring(0, 2));
    	int mm = Integer.parseInt(s.substring(3, 5));
    	int ss = Integer.parseInt(s.substring(6, 8));
    	String ampm = s.substring(8, 10);
    	if(ampm.equals("AM")) {
    		hr = (hr == 12)? 0 : hr;
    	}else{
    		hr = (hr == 12)? 12 : hr + 12;
    	};
		return String.format("%02d", hr) + ":" + String.format("%02d", mm)   + ":" + String.format("%02d", ss) ;
    }

}

public class TimeConversion {
    public static void main(String[] args) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

//        String s = bufferedReader.readLine();
        String s = "12:01:00AM";
        String result = TimeConversionResult.timeConversion(s);
        System.out.println(result);
        
        s = "12:01:00PM";
        result = TimeConversionResult.timeConversion(s);
        System.out.println(result);

        s = "12:59:59PM";
        result = TimeConversionResult.timeConversion(s);
        System.out.println(result);        

        
        s = "12:00:00AM";
        result = TimeConversionResult.timeConversion(s);
        System.out.println(result);
        
                

        
        s = "12:45:54PM";
        result = TimeConversionResult.timeConversion(s);
        System.out.println(result);
     
        
//        bufferedWriter.write(result);
//        bufferedWriter.newLine();
//
//        bufferedReader.close();
//        bufferedWriter.close();
    }
}
