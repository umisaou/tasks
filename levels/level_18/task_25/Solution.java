package com.javarush.task.task18.task1825;

import java.io.*;
import java.util.*;

/* 
Собираем файл
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Integer, String> map = new HashMap<>();
        String someName = "";
        while (true) {
            String filename = consoleReader.readLine();
            if (filename.equals("end"))
                break;

            String partsOfName[] = filename.split("\\.part");
            someName = partsOfName[0];
            map.put(Integer.parseInt(partsOfName[1]), filename);
        }

        BufferedOutputStream fileWriter = new BufferedOutputStream(new FileOutputStream(someName, true));
        for (int i = 1; i <= map.size(); i++) {
            BufferedInputStream fileReader = new BufferedInputStream(new FileInputStream(map.get(i)));
            byte[] buffer = new byte[fileReader.available()];
            fileReader.read(buffer);
            fileWriter.write(buffer);
            fileReader.close();
        }
        fileWriter.close();
        consoleReader.close();

    }
}
