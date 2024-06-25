package com.example.RaspberryDataServer.controllers;

import com.example.RaspberryDataServer.dto.FileEventDto;
import com.example.RaspberryDataServer.enums.FileEventEnum;
import com.example.RaspberryDataServer.services.FileEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/name")
    public List<FileEventDto> getAllEventByFileName(@RequestParam String name){
        return fileEventService.findAllEventByFileName(name);
    }

    @GetMapping("/ip")
    public List<FileEventDto> getAllEventByIp(@RequestParam String ip){
        return fileEventService.findAllEventByIp(ip);
    }

    @GetMapping("/day")
    public List<FileEventDto> getAllEventByDay(@RequestParam String day){
        return fileEventService.findAllEventByDay(day);
    }

    @GetMapping("/month")
    public List<FileEventDto> getAllEventByMonth(@RequestParam String month){
        return fileEventService.findAllEventByMonth(month);
    }

    @GetMapping("/year")
    public List<FileEventDto> getAllEventByYear(@RequestParam String year){
        return fileEventService.findAllEventByYear(year);
    }

    @GetMapping("/type")
    public List<FileEventDto> getAllEventByEventType(@RequestParam FileEventEnum event){
        return fileEventService.findAllEventByEventType(event);
    }

    @GetMapping("/{id}")
    public List<FileEventDto> getAllEventsByFileId(@PathVariable Long id){
        return fileEventService.getAllEventByFileId(id);
    }
}
