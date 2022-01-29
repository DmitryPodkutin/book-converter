package com.gmail.podkutin.dmitry.bookconverter.storage;

import java.io.File;

public interface Storage {
    File save(String userIpAddress, File file);

    boolean delete(String userIpAddress);

    File get(String userIpAddress);
}
