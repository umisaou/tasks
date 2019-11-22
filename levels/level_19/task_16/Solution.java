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
        ArrayList<String> file1 = new ArrayList<>();
        ArrayList<String> file2 = new ArrayList<>();
        while (true) {
            String s1 = fileReader1.readLine();
            String s2 = fileReader2.readLine();
            if (s1 != null)
                file1.add(s1);
            if (s2 != null)
                file2.add(s2);
            if (s1 == null && s2 == null)
                break;
        }
        
        int i, j;
        for (i = 0, j = 0; i < file1.size() && j < file2.size(); ) {
            String s1 = file1.get(i);
            String s2 = file2.get(j);
            if (!(s1.equals(s2))) {
                if (file1.get(i + 1).equals(s2)) {
                    lines.add(new LineItem(Type.REMOVED, s1));
                    i++;

                } else {
                    lines.add(new LineItem(Type.ADDED, s2));
                    j++;
                }
                continue;
            } else
                lines.add(new LineItem(Type.SAME, s1));
            i++;
            j++;
        }
        if (i < file1.size()) {
            String s = file1.get(i);
            lines.add(new LineItem(Type.REMOVED, s));
        } else if (j < file2.size()) {
            String s = file2.get(j);
            lines.add(new LineItem(Type.ADDED, s));
        }

        fileReader1.close();
        fileReader2.close();
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
