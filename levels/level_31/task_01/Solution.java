package com.javarush.task.task31.task3101;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
Проход по дереву файлов
*/
public class Solution {
    public static void main(String[] args) {
        File path = new File(args[0]);
        File resultFileAbsolutePath = new File(args[1]);
        File contentFile = new File(resultFileAbsolutePath.getParent() + "/allFilesContent.txt");
        FileUtils.renameFile(resultFileAbsolutePath, contentFile);
        FileWriter writer = null;
        try {
            writer = new FileWriter(contentFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<File> allFiles = getAllFilesFromDirectory(path);
        allFiles.sort(Comparator.comparing(File::getName));

        try {
            for (File file : allFiles) {
                FileReader reader = new FileReader(file);
                while (reader.ready()) {
                    writer.write(reader.read());
                }
                reader.close();
                writer.write('\n');
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public static List<File> getAllFilesFromDirectory(File directory) {
        List<File> allFiles = new ArrayList<>();
        for (File file : directory.listFiles()) {
            if (file.isDirectory())
                allFiles.addAll(getAllFilesFromDirectory(file));
            else if (file.length() <= 50)
                allFiles.add(file);
        }
        return allFiles;
    }
}
