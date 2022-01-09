package com.gmail.podkutin.dmitry.salesbookconverter.storage;

import java.io.File;

public interface Storage {
    File save(File file);

    boolean delete(String fileName);

    File get(String fileName);
}
