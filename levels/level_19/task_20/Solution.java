package com.javarush.task.testing;

/* 
Самый богатый
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

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
            map.merge(name, value, (aDouble, aDouble2) -> aDouble + aDouble2);

        }
        fileReader.close();

        TreeSet<String> set = new TreeSet<>();
        double max = 0;
        for (Map.Entry<String, Double> pair : map.entrySet()) {

            if (pair.getValue() > max) {
                set.clear();
                max = pair.getValue();
                set.add(pair.getKey());
            } else if (pair.getValue() == max) {
                set.add(pair.getKey());
            }
        }

        for (String s : set) {
            System.out.println(s);
        }
    }
}
