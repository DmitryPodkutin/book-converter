package com.gmail.podkutin.dmitry.salesbookconverter.config;

import com.gmail.podkutin.dmitry.salesbookconverter.model.*;
import com.gmail.podkutin.dmitry.salesbookconverter.util.ConverterObject;
import com.gmail.podkutin.dmitry.salesbookconverter.util.XmlJAXBParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.io.File;
import java.util.Objects;


@Configuration
public class ApplicationConfig {

    final
    Environment environment;

    public ApplicationConfig(@Autowired Environment environment) {
        this.environment = environment;
    }

    @Bean
    public File xsdFileSchema() {
        return new File(Objects.requireNonNull(environment.getProperty("xsd.schema.filePath")));
    }

    @Bean
    public XmlJAXBParser xmlJAXBParser() {
        return new XmlJAXBParser(SalesBook.class);
    }

    @Bean
    public  ConverterObject converterObject(){
        return  new ConverterObject();
    }

    @Bean
    public SalesBook salesBook() {
        return new SalesBook(environment.getProperty("sales.book.attribute"), document());
    }

    @Bean
    public Document document() {
        return new Document(sellerInformation(), salesBookDetails(), environment.getProperty("document.attribute"), signer());
    }

    @Bean
    public SellerInformation sellerInformation(){
        return new SellerInformation(new Requisites(environment.getProperty("innfl"),environment.getProperty("registrationInformation"),new FIO()));
    }

    @Bean
    public SalesBookDetails salesBookDetails(){
        return new SalesBookDetails();
    }

    @Bean
    public Signer signer(){
        return new Signer();
    }
}
