package com.javarush.task.task15.task1527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
Парсер реквестов
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<>();
        String url = r.readLine();
        String parametr = "";
        String alert = "";
        boolean isObject = false;
        boolean isParametr = false;
        for (int i = 0; i < url.length(); i++) {

            if (url.charAt(i) == '&' || url.charAt(i) == '?') {
                if (isParametr)
                    list.add(parametr);
                isParametr = true;
                parametr = "";
                if (isObject)
                    isObject = false;
                if (url.substring(i + 1, i + 4).equals("obj")) {
                    isObject = true;
                }


                continue;
            } else if (url.charAt(i) == '=') {
                isParametr = false;
                list.add(parametr);
                continue;
            }

            if (isParametr) {
                parametr += url.charAt(i);
            } else if (isObject) {
                alert += url.charAt(i);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        if (!(alert.isEmpty())) {
            try {
                alert(Double.parseDouble(alert));
            } catch (NumberFormatException e) {
                alert(alert);
            }
        }


    }

    public static void alert(double value) {
        System.out.println("\ndouble: " + value);
    }

    public static void alert(String value) {
        System.out.println("\nString: " + value);
    }
}
