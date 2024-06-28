package com.example.RaspberryDataServer.controllers;

import com.example.RaspberryDataServer.models.dto.FileEventDto;
import com.example.RaspberryDataServer.utility.enums.FileEventEnum;
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
    public List<FileEventDto> getAllEventByDay(@RequestParam String day,@RequestParam String month,@RequestParam String year){
        return fileEventService.findAllEventByDay(day,month,year);
    }

    @GetMapping("/month")
    public List<FileEventDto> getAllEventByMonth(@RequestParam String month,@RequestParam String year){
        return fileEventService.findAllEventByMonth(month,year);
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
