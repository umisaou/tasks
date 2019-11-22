package com.javarush.task.task31.task3106;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.TreeSet;
import java.util.Vector;
import java.util.zip.ZipInputStream;

/*
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String fileResultName = args[0];
        TreeSet<String> listOfParts = new TreeSet<>();
        for (int i = 1; i < args.length; i++) {
            listOfParts.add(args[i]);
        }
        Vector<FileInputStream> zipStreams = new Vector<>(args.length - 1);

        for (String filePartName : listOfParts) {
            zipStreams.add(new FileInputStream(filePartName));
        }

        SequenceInputStream sis = new SequenceInputStream(zipStreams.elements());
        ZipInputStream zis = new ZipInputStream(sis);

        FileOutputStream fos = new FileOutputStream(fileResultName);
        while ((zis.getNextEntry()) != null) {
            while (zis.available() > 0) {
                byte[] buffer = new byte[1024];
                int countBytes = zis.read(buffer);
                if (countBytes > 0)
                    fos.write(buffer, 0, countBytes);
            }
        }
        zis.close();
        fos.close();
    }
}
