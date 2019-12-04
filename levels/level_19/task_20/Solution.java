package com.javarush.task.testing;

/* 
Самый богатый
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader fileReader = new BufferedReader(new FileReader(args[0]));
        HashMap<String, Double> map = new HashMap<>();
        while (true) {
            String s = fileReader.readLine();
            if (s == null)
                break;
            String name = s.split(" ")[0];
            double value = Double.parseDouble(s.split(" ")[1]);
            map.merge(name, value, Double::sum);

        }
        fileReader.close();

        ArrayList<String> result = new ArrayList<>();
        double max = 0;
        for (Map.Entry<String, Double> pair : map.entrySet()) {

            if (pair.getValue() > max) {
                result.clear();
                max = pair.getValue();
                result.add(pair.getKey());
            } else if (pair.getValue() == max) {
                result.add(pair.getKey());
            }
        }
        result.sort(String::compareTo);
        for (String s : result) {
            System.out.println(s);
        }
    }
}
