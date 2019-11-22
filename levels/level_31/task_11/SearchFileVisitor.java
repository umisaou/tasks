package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private String partOfName;
    private String partOfContent;
    private int minSize;
    private int maxSize;
    private List<Path> foundFiles = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        byte[] content = Files.readAllBytes(file); // размер файла: content.length

        if (partOfName != null) {
            if (!file.getFileName().toString().contains(partOfName))
                return FileVisitResult.CONTINUE;
        }
        if (partOfContent != null) {

            if (!new String(content).contains(partOfContent))
                return FileVisitResult.CONTINUE;
        }
        if (maxSize > 0) {
            if (content.length > maxSize)
                return FileVisitResult.CONTINUE;
        }
        if (minSize > 0) {
            if (content.length < minSize)
                return FileVisitResult.CONTINUE;
        }

        foundFiles.add(file);
        return FileVisitResult.CONTINUE;
    }

    public void setPartOfName(String amigo) {
        this.partOfName = amigo;

    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;

    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;

    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;

    }

    public List<Path> getFoundFiles() {
        return foundFiles;
    }
}
