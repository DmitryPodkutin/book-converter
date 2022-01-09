package com.gmail.podkutin.dmitry.salesbookconverter.util;

import com.gmail.podkutin.dmitry.salesbookconverter.util.exception.NotFoundException;

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
}
