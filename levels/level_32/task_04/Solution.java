package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        boolean upperCheck = false;
        boolean lowerCheck = false;
        boolean numbCheck = false;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (int i = 0; i < 8; i++) {
            if (i >= 5) {
                if (!upperCheck) {
                    BAOSWriteUpperCase(baos);
                    continue;
                } else if (!lowerCheck) {
                    BAOSWriteLowerCase(baos);
                    continue;
                } else if (!numbCheck) {
                    BAOSWriteNumb(baos);
                    continue;
                }
            }

            switch ((int) (Math.random() * 3)) {
                case 0:
                    BAOSWriteUpperCase(baos);
                    upperCheck = true;
                    break;
                case 1:
                    BAOSWriteLowerCase(baos);
                    lowerCheck = true;
                    break;
                case 2:
                    BAOSWriteNumb(baos);
                    numbCheck = true;
                    break;
            }
        }
        return baos;
    }

    public static void BAOSWriteUpperCase(ByteArrayOutputStream baos) {
        baos.write((int) (Math.random() * 26 + 'A'));
    }

    public static void BAOSWriteLowerCase(ByteArrayOutputStream baos) {
        baos.write((int) (Math.random() * 26 + 'a'));
    }

    public static void BAOSWriteNumb(ByteArrayOutputStream baos) {
        baos.write((int) (Math.random() * 10 + '0'));
    }
}
