package com.gmail.podkutin.dmitry.bookconverter.controller;

import com.gmail.podkutin.dmitry.bookconverter.service.BookService;
import com.gmail.podkutin.dmitry.bookconverter.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.MalformedURLException;

@Controller
@PropertySource("classpath:messages.properties")
public class UiController {

    private final BookService service;
    @Value("${file_converted}")
    private String file_converted;
    @Value("${information}")
    private String information;

    public UiController(@Autowired BookService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String homePage() {
        return "sales_book";
    }

    // https://betacode.net/11765/spring-boot-file-download
    @PostMapping("/convert")
    public String convert(@RequestParam("file") MultipartFile file, HttpServletRequest request, Model model) {
        model.addAttribute("file_converted", file_converted);
        model.addAttribute("information", information);
        File fileToSave = service.getConvertFile(FileUtil.multipartFileToFile(file, file.getOriginalFilename()));
        if (service.saveFile(request.getRemoteAddr(), fileToSave) != null) {
            model.addAttribute("result", "converted");
        } else {
            model.addAttribute("result", "error");
        }
        return "sales_book";
    }

    //https://stackoverflow.com/questions/5673260/downloading-a-file-from-spring-controllers
    @GetMapping("/get_converted_file")
    @ResponseBody
    public ResponseEntity<Resource> getConvertedFile(HttpServletRequest request) {
        String userIpAddress = request.getRemoteAddr();
        try {
            Resource file = new UrlResource(service.getFile(userIpAddress).toURI());
            service.deleteFile(userIpAddress);
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, FileUtil.getContentDispositionWithCyrillicFileName(file)).body(file);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}