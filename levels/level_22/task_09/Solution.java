package com.javarush.task.task22.task2209;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/*
Составить цепочку слов
*/
public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fileReader = new BufferedReader(new FileReader(consoleReader.readLine()));
        consoleReader.close();
        String [] words = null;
        while (true) {
            String s = fileReader.readLine();
            if (s == null)
                break;
            words = s.split(" ");
        }

        fileReader.close();

        StringBuilder result = getLine(words);
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        if (words.length < 1)
            return new StringBuilder();
        StringBuilder result = null;
        for (int i = 0; ; i++) {
            ArrayList<String> list = new ArrayList<>(Arrays.asList(words));
            String symbol = list.get(i).charAt(list.get(i).length() - 1) + "";
            result = new StringBuilder(list.get(i));
            list.remove(i);
            boolean isEnd = false;
            while (!isEnd) {
                isEnd = true;
                for (int j = 0; j < list.size(); j++) {
                    if ((list.get(j).charAt(0) + "").equalsIgnoreCase(symbol)) {
                        result.append(" ").append(list.get(j));
                        symbol = list.get(j).charAt(list.get(j).length() - 1) + "";
                        list.remove(j);
                        isEnd = false;
                        break;
                    }
                }
            }
            if (list.size() == 0)
                break;
        }
        return result;
    }
}
