package com.aec.ems.util;

import java.io.File;
import java.io.IOException;

public class FileManager {

    private static final String FILE_NAME = "employees.txt";

    public static void createFile() {

        File file = new File(FILE_NAME);

        try {
            if (file.createNewFile()) {
                System.out.println("File created successfully.");
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("Error while creating file.");
            e.printStackTrace();
        }
    }

    public static String getFileName() {
        return FILE_NAME;
    }
}