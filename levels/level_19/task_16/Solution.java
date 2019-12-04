package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();


    public static void main(String[] args) throws Exception {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String name1 = consoleReader.readLine();
        String name2 = consoleReader.readLine();
        consoleReader.close();
        BufferedReader fileReader1 = new BufferedReader(new FileReader(name1));
        BufferedReader fileReader2 = new BufferedReader(new FileReader(name2));
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        while (true) {
            String s1 = fileReader1.readLine();
            String s2 = fileReader2.readLine();
            if (s1 != null)
                list1.add(s1);
            if (s2 != null)
                list2.add(s2);
            if (s1 == null && s2 == null)
                break;
        }
        fileReader1.close();
        fileReader2.close();

        int i, j;
        for (i = 0, j = 0; i < list1.size() && j < list2.size(); ) {
            String s1 = list1.get(i);
            String s2 = list2.get(j);
            if (s1.equals(s2)) {
                lines.add(new LineItem(Type.SAME, s1));
                i++;
                j++;
            } else {
                if (list1.get(i + 1).equals(s2)) {
                    lines.add(new LineItem(Type.REMOVED, s1));
                    i++;

                } else {
                    lines.add(new LineItem(Type.ADDED, s2));
                    j++;
                }
            }
        }

        if (i < list1.size()) {
            String s = list1.get(i);
            lines.add(new LineItem(Type.REMOVED, s));
        } else if (j < list2.size()) {
            String s = list2.get(j);
            lines.add(new LineItem(Type.ADDED, s));
        }


    }

    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
        
    }
}
