package com.gmail.podkutin.dmitry.salesbookconverter.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    public static File multipartToFile(MultipartFile multipart, String fileName) {
        File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
        try {
            multipart.transferTo(convFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convFile;
    }

    public static File getEmptyFile(String fileName) {
        return new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
    }
}
