package com.gmail.podkutin.dmitry.bookconverter.storage;

import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemoryStorage implements Storage {

    private final Map<String, File> storage = new ConcurrentHashMap<>();

    @Override
    public File save(String userIpAddress, File file) {
        storage.put(userIpAddress, file);
        return get(userIpAddress);
    }

    @Override
    public boolean delete(String userIpAddress) {
        return storage.remove(userIpAddress) != null;
    }

    @Override
    public File get(String userIpAddress) {
        return storage.get(userIpAddress);
    }
}
