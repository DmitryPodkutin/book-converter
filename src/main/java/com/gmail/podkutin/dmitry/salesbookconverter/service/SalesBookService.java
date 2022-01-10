package com.gmail.podkutin.dmitry.salesbookconverter.service;

import com.gmail.podkutin.dmitry.salesbookconverter.model.SalesBook;
import com.gmail.podkutin.dmitry.salesbookconverter.storage.Storage;
import com.gmail.podkutin.dmitry.salesbookconverter.util.ConverterObject;
import com.gmail.podkutin.dmitry.salesbookconverter.util.FileUtil;
import com.gmail.podkutin.dmitry.salesbookconverter.util.XmlJAXBParser;
import com.gmail.podkutin.dmitry.salesbookconverter.util.XmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.File;

import static com.gmail.podkutin.dmitry.salesbookconverter.util.checkExceptionUtil.checkNotFound;

@Service
public class SalesBookService {

    private final XmlJAXBParser parser;
    private final ConverterObject converterObject;
    private final File xsdFileSchema;
    private final Storage storage;

    public SalesBookService(@Autowired XmlJAXBParser parser, @Autowired ConverterObject converterObject, @Autowired File xsdFileSchema, @Autowired Storage storage) {
        this.parser = parser;
        this.converterObject = converterObject;
        this.xsdFileSchema = xsdFileSchema;
        this.storage = storage;
    }

    public File getFile(String userIpAddress) {
        checkNotFound(storage.get(userIpAddress), "File " + userIpAddress + " Not Found");
        return storage.get(userIpAddress);
    }

    public File saveFile(String userIpAddress, File file) {
        Assert.notNull(file, "file must not be null");
        return storage.save(userIpAddress, file);
    }

    public boolean deleteFile(String userIpAddress) {
        return checkNotFound(storage.delete(userIpAddress), userIpAddress + "not delete  because not found");
    }

    public File getConvertFile(File incoming) {
        Assert.notNull(incoming, "Incoming file must not be null");
        XmlUtil.validate(incoming, xsdFileSchema);
        File resultFile = FileUtil.getEmptyFile(incoming.getName());
        SalesBook salesBookIncoming = parser.unmarshall(incoming);
        parser.marshall(converterObject.convertSalesBookFor1C(salesBookIncoming), resultFile);
        return resultFile;
    }
}
