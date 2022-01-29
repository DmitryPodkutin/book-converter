package com.gmail.podkutin.dmitry.bookconverter.service;

import com.gmail.podkutin.dmitry.bookconverter.model.buyerBook.incoming.IncomingBuyerBook;
import com.gmail.podkutin.dmitry.bookconverter.model.salesBook.SalesBook;
import com.gmail.podkutin.dmitry.bookconverter.storage.Storage;
import com.gmail.podkutin.dmitry.bookconverter.util.ConverterObject;
import com.gmail.podkutin.dmitry.bookconverter.util.FileUtil;
import com.gmail.podkutin.dmitry.bookconverter.util.XmlJAXBParser;
import com.gmail.podkutin.dmitry.bookconverter.util.XmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.w3c.dom.Document;

import java.io.File;
import java.util.Objects;

import static com.gmail.podkutin.dmitry.bookconverter.util.checkExceptionUtil.checkFileForEmpty;
import static com.gmail.podkutin.dmitry.bookconverter.util.checkExceptionUtil.checkNotFound;

@Service
public class BookService {

    private final XmlJAXBParser salesBookParser;
    private final XmlJAXBParser buyerBookParser;
    private final ConverterObject converterObject;
    private final File salesBookXsdFileSchema;
    private final File buyerBookXsdFileSchema;
    private final Storage storage;

    public BookService(@Qualifier("salesBookXmlJAXBParser") @Autowired XmlJAXBParser salesBookParser,
                       @Qualifier("buyerBookXmlJAXBParser") @Autowired XmlJAXBParser buyerBookParser,
                       @Autowired ConverterObject converterObject,
                       @Autowired File salesBookXsdFileSchema,
                       @Autowired File buyerBookXsdFileSchema,
                       @Autowired Storage storage) {
        this.salesBookParser = salesBookParser;
        this.buyerBookParser = buyerBookParser;
        this.converterObject = converterObject;
        this.salesBookXsdFileSchema = salesBookXsdFileSchema;
        this.buyerBookXsdFileSchema = buyerBookXsdFileSchema;
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
        checkFileForEmpty(incoming);
        File resultFile = FileUtil.getEmptyFile(incoming.getName());
        Document document = XmlUtil.getDocumentObjectModel(incoming);

        if (XmlUtil.tagIsPresentInXMLFile(Objects.requireNonNull(document), "СвПокуп")) {
            XmlUtil.validate(incoming, buyerBookXsdFileSchema);
            IncomingBuyerBook salesBookIncoming = buyerBookParser.unmarshall(incoming);
            buyerBookParser.marshall(converterObject.convertBuyerBookForTaxpayer(salesBookIncoming), resultFile);
        } else if (XmlUtil.tagIsPresentInXMLFile(document, "СвПродав")) {
            XmlUtil.validate(incoming, salesBookXsdFileSchema);
            SalesBook salesBookIncoming = salesBookParser.unmarshall(incoming);
            salesBookParser.marshall(converterObject.convertSalesBookForTaxpayer(salesBookIncoming), resultFile);
        } else {
            throw new RuntimeException("XML File Не соответсвует формату");
        }
        return resultFile;
    }
}
