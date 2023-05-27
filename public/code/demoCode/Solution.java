package demoCode;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;


class Result {

    /*
     * Complete the 'print_classification' function below.
     *
     * The function accepts 2D_INTEGER_ARRAY raw_data as parameter.
     */

    public static void print_classification(List<List<Integer>> raw_data) {
        
        for (List<Integer> list : raw_data) {
            
            System.out.println("list="+list.toString());
            for (int i = 0; i < list.size(); i++) {
                System.out.println("list_element:"+list.get(i));
                
                switch (list.get(i)){
                    
                    case 1001:
                    break;
                    
                    case 1002:
                    break;
                    
                    case 1003:
                    break;
                    
                }
            }
        }
        
        
        String a =raw_data.toString();
        System.out.println("Hello,World"+a);
    }

}
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int raw_dataRows = Integer.parseInt(bufferedReader.readLine().trim());
        int raw_dataColumns = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> raw_data = new ArrayList<>();

        for (int i = 0; i < raw_dataRows; i++) {
            String[] raw_dataRowTempItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            List<Integer> raw_dataRowItems = new ArrayList<>();

            for (int j = 0; j < raw_dataColumns; j++) {
                int raw_dataItem = Integer.parseInt(raw_dataRowTempItems[j]);
                raw_dataRowItems.add(raw_dataItem);
            }

            raw_data.add(raw_dataRowItems);
        }

        Result.print_classification(raw_data);

        bufferedReader.close();
    }
}
