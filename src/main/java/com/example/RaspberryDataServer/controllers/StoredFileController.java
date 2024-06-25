package com.example.RaspberryDataServer.controllers;

import com.example.RaspberryDataServer.dto.FileEventDto;
import com.example.RaspberryDataServer.dto.StoredFileDto;
import com.example.RaspberryDataServer.entities.FileEvent;
import com.example.RaspberryDataServer.services.StoredFileService;
import jakarta.servlet.http.HttpServletRequest;
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
public class StoredFileController {
    @Autowired
    private StoredFileService storedFileService;


    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException {
        return storedFileService.upload(file, request);
    }
    @PostMapping("/jar")
    public String uploadJar(@RequestParam MultipartFile file) throws IOException {
        return storedFileService.uploadNewJar(file);
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
        return storedFileService.download(file);
    }
    @GetMapping("/all")
    public List<StoredFileDto> getAllFiles(){
        return storedFileService.getAllFiles();
    }
    @GetMapping
    public List<StoredFileDto> getNotDeletedFiles(){
        return storedFileService.getAllFilesNotDeleted();
    }

    @DeleteMapping()
    public String deleteFile(@RequestParam String fileName,HttpServletRequest request ){
        return storedFileService.deleteFile(fileName,request);
    }
    @PatchMapping("/edit")
    public String editName(@RequestParam String fileName,@RequestParam String ext, @RequestParam String newName, HttpServletRequest request) throws IOException {
        return storedFileService.edit(fileName,ext,newName,request);
    }
    @DeleteMapping("/clean")
    public String deleteAll(){
        storedFileService.cleanDb();
        return "Database pulito";
    }
}
