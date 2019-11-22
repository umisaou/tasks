package com.javarush.task.task18.task1827;

/*
Прайсы
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = consoleReader.readLine();
        if (args.length > 1 && args[0].equals("-c")) {

            String parameters = getID(fileName) + getProductName(args) + getPrice(args) + getQuantity(args);
            BufferedOutputStream fileWriter = new BufferedOutputStream(new FileOutputStream(fileName, true));
            fileWriter.write('\n');
            fileWriter.write(parameters.getBytes());
            fileWriter.close();
        }
    }

    public static String getID(String fileName) throws Exception {

        ArrayList<Integer> listID = new ArrayList<>();
        BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
        while (true) {
            String s = fileReader.readLine();
            if (s == null)
                break;
            listID.add(Integer.parseInt(s.split("\\D")[0]));
        }
        Collections.sort(listID);
        String id = (listID.get(listID.size() - 1) + 1) + "";
        if (id.length() > 8)
            id = id.substring(0, 8);
        else if (id.length() < 8)
            id += spaceAdd(8 - id.length());
        fileReader.close();
        return id;
    }

    public static String getProductName(String[] args) {
        String productName = args[1];
        for (int i = 2; i < args.length - 2; i++) {
            productName += " " + args[i];
        }
        if (productName.length() > 30)
            productName = productName.substring(0, 30);
        else if (productName.length() < 30)
            productName += spaceAdd(30 - productName.length());
        return productName;
    }

    public static String getPrice(String[] args) {
        String price = args[args.length - 2];

        if (price.length() > 8)
            price = price.substring(0, 8);
        else if (price.length() < 8)
            price += spaceAdd(8 - price.length());
        return price;
    }

    public static String getQuantity(String[] args) {
        String quantity = args[args.length - 1];

        if (quantity.length() > 4)
            quantity = quantity.substring(0, 4);
        else if (quantity.length() < 4)
            quantity += spaceAdd(4 - quantity.length());
        return quantity;
    }

    public static String spaceAdd(int numberOfSpace) {
        String spaces = "";
        for (int i = 0; i < numberOfSpace; i++) {
            spaces += " ";
        }
        return spaces;
    }
}
