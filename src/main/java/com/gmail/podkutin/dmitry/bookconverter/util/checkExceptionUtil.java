package com.gmail.podkutin.dmitry.bookconverter.util;

import com.gmail.podkutin.dmitry.bookconverter.util.exception.NotFoundException;

import java.io.File;

public class checkExceptionUtil {

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static boolean checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("File with name" + msg);
        }
        return true;
    }

    public static void checkFileForEmpty(File file) {
        if (FileUtil.isFileEmpty(file)) {
            throw new RuntimeException("File is empty");
        }
    }
}
