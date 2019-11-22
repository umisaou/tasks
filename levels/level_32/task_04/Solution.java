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
        boolean upCheck = false;
        boolean downCheck = false;
        boolean numbCheck = false;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (int i = 0; i < 8; i++) {
            if (i >= 5) {
                if (!upCheck) {
                    BAOSWriteUpCase(baos);
                    continue;
                } else if (!downCheck) {
                    BAOSWriteDownCase(baos);
                    continue;
                } else if (!numbCheck) {
                    BAOSWriteNumb(baos);
                    continue;
                }
            }

            switch ((int) (Math.random() * 3)) {
                case 0:
                    BAOSWriteUpCase(baos);
                    upCheck = true;
                    break;
                case 1:
                    BAOSWriteDownCase(baos);
                    downCheck = true;
                    break;
                case 2:
                    BAOSWriteNumb(baos);
                    numbCheck = true;
                    break;
            }
        }
        return baos;
    }

    public static void BAOSWriteUpCase(ByteArrayOutputStream baos) {
        baos.write((int) (Math.random() * ('Z' - 'A' + 1) + 'A'));
    }

    public static void BAOSWriteDownCase(ByteArrayOutputStream baos) {
        baos.write((int) (Math.random() * ('z' - 'a' + 1) + 'a'));
    }

    public static void BAOSWriteNumb(ByteArrayOutputStream baos) {
        baos.write((int) (Math.random() * ('1' - '0' + 1) + '0'));
    }
}
