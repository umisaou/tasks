package com.javarush.task.task22.task2212;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/*
Проверка номера телефона
*/
public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        if (telNumber == null || telNumber.isEmpty())
            return false;
        if (telNumber.charAt(0) == '+') {
            if (telNumber.matches("^\\+\\d+(\\(\\d{3}\\))?\\d+-?\\d+-?\\d+")) {
                if (telNumber.replaceAll("\\D+", "").length() == 12) {
                    System.out.println(telNumber.length());
                    return true;
                }
            }
        } else if (telNumber.charAt(0) == '(') {
            if (telNumber.matches("^\\(\\d{3}\\)\\d+-?\\d+-?\\d+")) {
                if (telNumber.replaceAll("\\D+", "").length() == 10) {
                    System.out.println(telNumber.length());
                    return true;
                }
            }
        } else {
            if (telNumber.matches("^\\d+(\\(\\d{3}\\))?\\d+-?\\d+-?\\d+")) {
                if (telNumber.replaceAll("\\D+", "").length() == 10) {
                    System.out.println(telNumber.length());
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));
        while (true) {
            String s = reader.readLine();
            if (s == null)
                break;
            System.out.println(s + " " + checkTelNumber(s));
        }
        checkTelNumber(null);
    }
}
