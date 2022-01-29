package com.gmail.podkutin.dmitry.bookconverter.config;

import com.gmail.podkutin.dmitry.bookconverter.model.*;
import com.gmail.podkutin.dmitry.bookconverter.model.buyerBook.incoming.IncomingBuyerBook;
import com.gmail.podkutin.dmitry.bookconverter.model.buyerBook.result.ResultBuyerBook;
import com.gmail.podkutin.dmitry.bookconverter.model.salesBook.SalesDocument;
import com.gmail.podkutin.dmitry.bookconverter.model.salesBook.SalesBook;
import com.gmail.podkutin.dmitry.bookconverter.util.ConverterObject;
import com.gmail.podkutin.dmitry.bookconverter.util.XmlJAXBParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.io.File;
import java.util.Objects;


@Configuration
public class ApplicationConfig {

    final Environment environment;

    public ApplicationConfig(@Autowired Environment environment) {
        this.environment = environment;
    }

    @Bean
    public File salesBookXsdFileSchema() {
        return new File(Objects.requireNonNull(environment.getProperty("sales.book.xsd.schema.filePath")));
    }

    @Bean
    public File buyerBookXsdFileSchema() {
        return new File(Objects.requireNonNull(environment.getProperty("buyer.book.xsd.schema.filePath")));
    }

    @Bean
    public XmlJAXBParser salesBookXmlJAXBParser() {
        return new XmlJAXBParser(SalesBook.class);
    }

    @Bean
    public XmlJAXBParser buyerBookXmlJAXBParser() {
        return new XmlJAXBParser(ResultBuyerBook.class, IncomingBuyerBook.class);
    }

    @Bean
    public ConverterObject converterObject() {
        return new ConverterObject();
    }

    @Bean
    public SalesDocument salesDocument() {
        return new SalesDocument(informationAboutOrganization());
    }

    @Bean
    public InformationAboutOrganization informationAboutOrganization() {
        return new InformationAboutOrganization(new Requisites(environment.getProperty("innfl"), environment.getProperty("registrationInformation"), new FIO()));
    }
}
