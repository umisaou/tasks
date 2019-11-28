package com.javarush.task.task18.task1825;

import java.io.*;
import java.util.*;

/* 
Собираем файл
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Integer, String> partsOfFile = new HashMap<>();
        String resultFileName = "";
        while (true) {
            String filename = consoleReader.readLine();
            if (filename.equals("end"))
                break;

            String partsOfName[] = filename.split("\\.part");
            resultFileName = partsOfName[0];
            partsOfFile.put(Integer.parseInt(partsOfName[1]), filename);
        }

        BufferedOutputStream fileWriter = new BufferedOutputStream(new FileOutputStream(resultFileName, true));
        for (int i = 1; i <= partsOfFile.size(); i++) {
            BufferedInputStream fileReader = new BufferedInputStream(new FileInputStream(partsOfFile.get(i)));
            byte[] buffer = new byte[fileReader.available()];
            fileReader.read(buffer);
            fileWriter.write(buffer);
            fileReader.close();
        }
        fileWriter.close();
        consoleReader.close();

    }
}
