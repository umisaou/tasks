package com.javarush.task.task31.task3105;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        addFileToArchive(args[0], args[1]);
        
    }

    public static Map<ZipEntry, ByteArrayOutputStream> createTempArchive(String archiveName) throws IOException {
        Map<ZipEntry, ByteArrayOutputStream> temp = new HashMap<>();
        FileInputStream fis = new FileInputStream(archiveName);
        ZipInputStream zin = new ZipInputStream(fis);
        ZipEntry zipEntry;
        while ((zipEntry = zin.getNextEntry()) != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            while (zin.available() > 0) {
                byte[] buffer = new byte[1024];
                int countBytes = zin.read(buffer);
                if (countBytes > 0)
                    baos.write(buffer, 0, countBytes);
            }
            temp.put(zipEntry, baos);
        }
        fis.close();
        zin.close();

        return temp;
    }

    public static void addFileToArchive(String fileName, String archiveName) throws IOException {
        Map<ZipEntry, ByteArrayOutputStream> temp = createTempArchive(archiveName);
        FileOutputStream fos = new FileOutputStream(archiveName);
        ZipOutputStream zout = new ZipOutputStream(fos);
        boolean isFileAdd = false;
        for (ZipEntry zipEntry : temp.keySet()) {
            byte[] dataBuffer = temp.get(zipEntry).toByteArray();
            String zipEntryName = zipEntry.getName();
            if (zipEntry.getName().equals(Paths.get(fileName).getFileName().toString())) {
                Files.copy(Paths.get(fileName), zout);
                isFileAdd = true;
                continue;
            }
            zout.putNextEntry(new ZipEntry(zipEntryName));
            zout.write(dataBuffer);
        }
        if (!isFileAdd) {
            Files.copy(Paths.get(fileName), zout);
        }
        zout.close();
        fos.close();

    }

}
