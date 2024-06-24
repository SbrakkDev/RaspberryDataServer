package com.example.RaspberryDataServer.controllers;

import com.example.RaspberryDataServer.dto.FileEventDto;
import com.example.RaspberryDataServer.services.FileEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/event")
public class FileEventController {

    @Autowired
    private FileEventService fileEventService;

    @GetMapping()
    public List<FileEventDto> getAllEvent(){
        return fileEventService.findAllEvent();
    }
}
