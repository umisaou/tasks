package com.javarush.task.task19.task1918;

/* 
Знакомство с тегами
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        FileReader fileReader = new FileReader(consoleReader.readLine());
        consoleReader.close();
        ArrayList<String> tags = new ArrayList<>();
        ArrayList<String> buffer = new ArrayList<>();
        String file = "";
        String tag = "";
        boolean inTag = false;
        int tagDeep = 0;
        while (fileReader.ready()) {
            file += (char) fileReader.read();
        }
        fileReader.close();
        for (int i = 0; i < file.length(); i++) {
            char symbol = file.charAt(i);
            if (symbol == '<') {
                inTag = true;
                if (file.charAt(i + 1) == '/') {
                    tagDeep--;
                } else {
                    tagDeep++;
                }
            }
            if (tagDeep > 0 || inTag) {
                if (symbol != '\n' && symbol != '\r' && symbol != '\t')
                    tag += symbol;
            }
            if (symbol == '>' && tagDeep == 0) {
                inTag = false;
                buffer.add(tag);
                tag = "";
            }
        }
        for (int i = 0; i < buffer.size(); i++) {
            tags.add(buffer.get(i));
            int countTags = buffer.get(i).split("</").length - 1;
            if (countTags > 1) {
                for (int j = 0; j < countTags - 1; j++) {
                    StringBuffer s = new StringBuffer();
                    s.append(tags.get(tags.size() - 1).replaceFirst("<.+?>", ""));
                    String str = s.reverse().toString().replaceFirst(">.+?<", "");
                    s.delete(0, s.length());
                    s.append(str);
                    s.reverse();
                    tags.add(s.toString());
                }
            }
        }

        for (int i = 0; i < tags.size(); i++) {
            String s = "";
            for (int j = 1; ; j++) {
                char symbol = tags.get(i).charAt(j);
                if (symbol == '>' || symbol == ' ')
                    break;
                s += symbol;
            }
            if (s.equals(args[0]))
                System.out.println(tags.get(i));
        }
    }
}
