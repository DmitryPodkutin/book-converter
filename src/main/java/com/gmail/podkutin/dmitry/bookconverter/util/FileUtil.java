package com.gmail.podkutin.dmitry.bookconverter.util;

import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

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

    //    https://stackoverflow.com/questions/53961134/how-to-return-downloaded-file-name-in-cyrillic
    public static String getContentDispositionWithCyrillicFileName(Resource file) {
        ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                .filename(Objects.requireNonNull(Objects.requireNonNull(file.getFilename()).replace("%20", "_")), StandardCharsets.UTF_8)
                .build();
        return contentDisposition.toString();
    }

    public static boolean isFileEmpty(File file) {
        return file.length() == 0;
    }
}
