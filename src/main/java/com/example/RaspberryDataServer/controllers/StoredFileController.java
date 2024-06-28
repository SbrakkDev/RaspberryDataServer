package com.example.RaspberryDataServer.controllers;

import com.example.RaspberryDataServer.models.entities.FileEvent;
import com.example.RaspberryDataServer.models.dto.StoredFileDto;
import com.example.RaspberryDataServer.models.http.HttpResponseInvalid;
import com.example.RaspberryDataServer.models.http.HttpResponseValid;
import com.example.RaspberryDataServer.services.StoredFileService;
import com.example.RaspberryDataServer.utility.messages.Codes;
import com.example.RaspberryDataServer.utility.messages.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> upload(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException {
        try {
            FileEvent newEvent = storedFileService.upload(file, request);
            return ResponseEntity.ok(new HttpResponseValid(Codes.OK_CODE, newEvent));
        }catch (IOException e){
            return ResponseEntity.badRequest().body(new HttpResponseInvalid(Codes.ERROR_CODE, ErrorMessage.ERROR_WRONG_PATH));
        }

    }

    @GetMapping("/download")
    public ResponseEntity<?> download(@RequestParam String file, HttpServletResponse response) throws IOException {
        response.setHeader("Content-Disposition", "attachment; filename=\""+file+"\"");
        try{
            byte[] download = storedFileService.download(file);
            return ResponseEntity.ok(new HttpResponseValid(Codes.OK_CODE, download));
        }catch (IOException e) {
            return ResponseEntity.badRequest().body(new HttpResponseInvalid(Codes.ERROR_CODE, ErrorMessage.ERROR_FILE_NOT_FOUD));
        }
    }


    @GetMapping
    public List<StoredFileDto> getNotDeletedFiles(){
        return storedFileService.getAllFilesNotDeleted();
    }

    @DeleteMapping()
    public String deleteFile(@RequestParam String fileName,String ext,HttpServletRequest request ){
        return storedFileService.deleteFile(fileName,ext, request);
    }
    @PatchMapping("/edit")
    public String editName(@RequestParam String fileName,@RequestParam String ext, @RequestParam String newName, HttpServletRequest request) throws IOException {
        return storedFileService.edit(fileName,ext,newName,request);
    }

}
