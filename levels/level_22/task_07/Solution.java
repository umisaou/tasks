package com.javarush.task.task22.task2207;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(consoleReader.readLine())));
        consoleReader.close();
        ArrayList<String> words = new ArrayList<>();
        while (true) {
            String s = fileReader.readLine();
            if (s == null)
                break;
            String arrS[] = s.split(" ");

            for (int i = 0; i < arrS.length; i++) {

                words.add(arrS[i]);
            }
        }
        fileReader.close();

        while (words.size() > 0) {
            StringBuilder word = new StringBuilder(words.get(0));
            StringBuilder wordR = new StringBuilder(word.toString());
            wordR.reverse();
            words.remove(0);
            if (words.contains(wordR.toString())) {
                Pair pair = new Pair();
                pair.first = word.toString();
                pair.second = wordR.toString();
                result.add(pair);
                words.remove(wordR.toString());
            }
        }
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }


    public static class Pair {
        String first;
        String second;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;
        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return first == null && second == null ? "" :
                    first == null ? second :
                            second == null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
