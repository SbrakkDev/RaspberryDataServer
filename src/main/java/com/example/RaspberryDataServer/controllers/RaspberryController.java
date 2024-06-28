package com.example.RaspberryDataServer.controllers;

import com.example.RaspberryDataServer.exceptions.SystemException;
import com.example.RaspberryDataServer.models.dto.StoredFileDto;
import com.example.RaspberryDataServer.models.dto.SystemUpdateEventDto;
import com.example.RaspberryDataServer.models.http.HttpResponseInvalid;
import com.example.RaspberryDataServer.models.http.HttpResponseValid;
import com.example.RaspberryDataServer.services.RaspberryService;
import com.example.RaspberryDataServer.utility.messages.Codes;
import com.example.RaspberryDataServer.utility.messages.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/raspberry")
public class RaspberryController {

    @Autowired
    private RaspberryService raspberryService;

    @PostMapping("/update")
    public ResponseEntity<?> updateSystem(@RequestParam MultipartFile file) throws IOException {
        try {
            raspberryService.updateApp(file);
            return ResponseEntity.ok(new HttpResponseValid(Codes.OK_CODE, "Raspberry .jar file updated succesfully!, please restart the server"));
        }catch (IOException e){
            return ResponseEntity.badRequest().body(new HttpResponseInvalid(Codes.ERROR_CODE, ErrorMessage.ERROR_WRONG_PATH));
        } catch (SystemException e) {
            return ResponseEntity.badRequest().body(new HttpResponseInvalid(Codes.IGNORED_CODE, ErrorMessage.SYSTEM_UP_TO_DATE));
        }
    }

    @GetMapping("/updates")
    public ResponseEntity<?> getAllUpdates(){
        List<SystemUpdateEventDto> updates = raspberryService.getAllUpdate();
        return ResponseEntity.ok(new HttpResponseValid(Codes.OK_CODE,updates));
    }

    @DeleteMapping("/clean")
    public String deleteAll(){
        raspberryService.cleanDb();
        return "Database pulito";
    }

    @GetMapping("/deleted")
    public ResponseEntity<?> getAllFiles(){
        List<StoredFileDto> deletedFiles = raspberryService.getAllDeletedFiles();
        return ResponseEntity.ok(new HttpResponseValid(Codes.OK_CODE,deletedFiles));
    }

}
