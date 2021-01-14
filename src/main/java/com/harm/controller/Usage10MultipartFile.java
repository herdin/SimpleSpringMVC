package com.harm.controller;

import com.harm.vo.Event;
import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

@Controller
@RequestMapping("/usage10")
public class Usage10MultipartFile {
    private Logger logger = LoggerFactory.getLogger(Usage10MultipartFile.class);
    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping("/file")
    public String show(Model model) {
        return "/file/fileupload";
    }

    @PostMapping("/file")
    public String upload(@RequestParam("uploadFile") MultipartFile file, RedirectAttributes redirectAttributes) {
        logger.debug("upload file name -> {}", file.getOriginalFilename());
        logger.debug("upload file size -> {}", file.getSize());
        redirectAttributes.addFlashAttribute("message", "server got " + file.getOriginalFilename());
        return "redirect:/usage10/file";
    }

    @GetMapping("/file/{filename}")
    public ResponseEntity<Resource> get(@PathVariable String filename) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:/" + filename);
        Tika tika = new Tika();
        String mediaType = tika.detect(resource.getFile());
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .header(HttpHeaders.CONTENT_TYPE, mediaType)
                .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(resource.getFile().length()))
                .body(resource);
    }


}
