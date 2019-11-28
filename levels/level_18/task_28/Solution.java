package com.javarush.task.task18.task1828;

/* 
Прайсы 2
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> buffer = new ArrayList<>();
        String fileName = consoleReader.readLine();
        if (args.length > 1) {
            BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
            while (true) {
                String s = fileReader.readLine();
                if (s == null)
                    break;
                String id = s.split("\\D")[0];
                if (id.equals(args[1])) {
                    if (args[0].equals("-u")) {
                        s = id + spaceAdd(8 - id.length()) + getProductName(args) + getPrice(args) + getQuantity(args);

                    } else if (args[0].equals("-d")) {
                        continue;
                    }
                }
                buffer.add(s);
            }
            fileReader.close();

            BufferedOutputStream fileWriter = new BufferedOutputStream(new FileOutputStream(fileName));
            for (int i = 0; i < buffer.size(); i++) {
                fileWriter.write(buffer.get(i).getBytes());
                fileWriter.write('\n');
            }
            fileWriter.close();
        }
        consoleReader.close();
    }

    public static String getProductName(String[] args) {
        String productName = args[2];
        for (int i = 3; i < args.length - 2; i++) {
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
