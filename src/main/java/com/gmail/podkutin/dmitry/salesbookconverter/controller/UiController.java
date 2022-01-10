package com.gmail.podkutin.dmitry.salesbookconverter.controller;

import com.gmail.podkutin.dmitry.salesbookconverter.service.SalesBookService;
import com.gmail.podkutin.dmitry.salesbookconverter.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;

@Controller
public class UiController {

    private final SalesBookService service;

    public UiController(@Autowired SalesBookService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String homePage() {
        return "sales_book";
    }

    // https://betacode.net/11765/spring-boot-file-download
    @PostMapping("/convert")
    public String convert(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String userIpAddress = request.getRemoteAddr();
        service.saveFile(userIpAddress, service.getConvertFile(FileUtil.multipartToFile(file, file.getOriginalFilename())));
        return "redirect:file/" + userIpAddress;
    }

    //https://stackoverflow.com/questions/5673260/downloading-a-file-from-spring-controllers
    @GetMapping("/file/{userIpAddress}")
    @ResponseBody
    public ResponseEntity<Resource> getConvertFile(@PathVariable String userIpAddress) {
        Resource file = null;
        try {
            file = new UrlResource(service.getFile(userIpAddress).toURI());
            service.deleteFile(userIpAddress);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + "result.xml" + "\"").body(file);
    }
}