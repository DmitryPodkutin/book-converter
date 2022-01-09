package com.gmail.podkutin.dmitry.salesbookconverter.storage;

import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemoryStorage implements Storage {

    private final Map<String, File> storage = new ConcurrentHashMap<>();

    @Override
    public File save(File file) {
        storage.put(file.getName(), file);
        return get(file.getName());
    }

    @Override
    public boolean delete(String fileName) {
        return storage.remove(fileName) != null;
    }

    @Override
    public File get(String fileName) {
        return storage.get(fileName);
    }
}
