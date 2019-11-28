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
        String parameter = "";
        String alert = "";
        boolean inObject = false;
        boolean inParameter = false;
        for (int i = 0; i < url.length(); i++) {

            if (url.charAt(i) == '&' || url.charAt(i) == '?') {
                if (inParameter)
                    list.add(parameter);
                inParameter = true;
                parameter = "";
                if (inObject)
                    inObject = false;
                if (url.substring(i + 1, i + 4).equals("obj")) {
                    inObject = true;
                }


                continue;
            } else if (url.charAt(i) == '=') {
                inParameter = false;
                list.add(parameter);
                continue;
            }

            if (inParameter) {
                parameter += url.charAt(i);
            } else if (inObject) {
                alert += url.charAt(i);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        if (!(alert.isEmpty())) {
            if( alert.contains("."))
               alert(Double.parseDouble(alert));
           else
               alert(alert);
        }


    }

    public static void alert(double value) {
        System.out.println("\ndouble: " + value);
    }

    public static void alert(String value) {
        System.out.println("\nString: " + value);
    }
}
