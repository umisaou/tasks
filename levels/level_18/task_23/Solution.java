package com.javarush.task.task18.task1823;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Нити и байты
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws Exception {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String fileName = consoleReader.readLine();
            if (fileName.equals("exit")) {
                consoleReader.close();
                break;
            }
            new ReadThread(fileName).start();
        }
        System.out.println(resultMap);
    }

    public static class ReadThread extends Thread {
        public String fileName = "";

        public ReadThread(String fileName) {
            this.fileName = fileName;

        }

        public void run() {
            BufferedInputStream fileReader = null;
            int[] arrByte = new int[128];
            try {
                fileReader = new BufferedInputStream(new FileInputStream(fileName));
                while (fileReader.available() > 0) {
                    arrByte[fileReader.read()]++;
                }
                fileReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            int max = arrByte[0];
            int symbol = 0;
            for (int i = 0; i < arrByte.length; i++) {
                if (arrByte[i] > max) {
                    max = arrByte[i];
                    symbol = i;
                }
            }
            synchronized (resultMap) {
                resultMap.put(fileName, symbol);
            }
        }

    }
}
