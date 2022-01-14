package com.gmail.podkutin.dmitry.salesbookconverter.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    public static File multipartFileToFile(MultipartFile multipartFile, String fileName) {
        File file = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static File getEmptyFile(String fileName) {
        return new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
    }
}
