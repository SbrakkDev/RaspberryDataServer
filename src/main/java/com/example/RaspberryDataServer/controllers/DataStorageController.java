package com.example.RaspberryDataServer.controllers;

import com.example.RaspberryDataServer.dto.DataStorageDto;
import com.example.RaspberryDataServer.entities.DataStorage;
import com.example.RaspberryDataServer.services.DataStorageService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/data")
public class DataStorageController {
    @Autowired
    private DataStorageService dataStorageService;

    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        return dataStorageService.upload(file);
    }
    @PostMapping("/jar")
    public String uploadJar(@RequestParam MultipartFile file) throws IOException {
        return dataStorageService.uploadNewJar(file);
    }

    @GetMapping("/download")
    public byte[] download(@RequestParam String file, HttpServletResponse response) throws IOException {
        String ext = FilenameUtils.getExtension(file);
        switch (ext){
            case "gif":
                response.setContentType(MediaType.IMAGE_GIF_VALUE);
                break;
            case "jpg":
            case "jpeg":
                response.setContentType(MediaType.IMAGE_JPEG_VALUE);
                break;
            case "png":
                response.setContentType(MediaType.IMAGE_PNG_VALUE);
                break;
            case "pdf":
                response.setContentType(MediaType.APPLICATION_PDF_VALUE);
                break;
        }
        response.setHeader("Content-Disposition", "attachment; filename=\""+file+"\"");
        return dataStorageService.download(file);
    }
    @GetMapping("/files")
    public List<DataStorageDto> getAllFiles(){
        return dataStorageService.getAllFiles();
    }

    @DeleteMapping()
    public String deleteFile(@RequestParam String fileName){
        return dataStorageService.deleteFile(fileName);
    }
    @DeleteMapping("/clean")
    public String deleteAll(){
        dataStorageService.cleanDb();
        return "Database pulito";
    }
}
