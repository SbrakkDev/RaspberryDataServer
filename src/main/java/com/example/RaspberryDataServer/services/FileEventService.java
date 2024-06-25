package com.example.RaspberryDataServer.services;

import com.example.RaspberryDataServer.dto.FileEventDto;
import com.example.RaspberryDataServer.entities.FileEvent;
import com.example.RaspberryDataServer.entities.StoredFile;
import com.example.RaspberryDataServer.enums.FileEventEnum;
import com.example.RaspberryDataServer.mappers.FileEventMapper;
import com.example.RaspberryDataServer.repositories.FileEventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class FileEventService {

    @Autowired
    private FileEventRepo fileEventRepo;
    @Autowired
    private FileEventMapper map;

    public List<FileEventDto> findAllEvent() {
        List<FileEvent> fileEvents = fileEventRepo.findAll();
        List<FileEventDto> fileEventDtos = new ArrayList<>();
        for(FileEvent fileEvent : fileEvents){
            fileEventDtos.add(map.entityToDto(fileEvent));
        }
        return fileEventDtos;
    }

    public List<FileEventDto> findAllEventByFileName(String name){
        List<FileEvent> fileEvents = fileEventRepo.findAllEventByFileName(name);
        List<FileEventDto> fileEventDtos = new ArrayList<>();
        for(FileEvent fileEvent : fileEvents){
            fileEventDtos.add(map.entityToDto(fileEvent));
        }
        return fileEventDtos;
    }

    public List<FileEventDto> findAllEventByIp(String ip){
        List<FileEvent> fileEvents = fileEventRepo.findAllEventByIp(ip);
        List<FileEventDto> fileEventDtos = new ArrayList<>();
        for(FileEvent fileEvent : fileEvents){
            fileEventDtos.add(map.entityToDto(fileEvent));
        }
        return fileEventDtos;
    }

    public List<FileEventDto> findAllEventByEventType(FileEventEnum event){
        List<FileEvent> fileEvents = fileEventRepo.findAllEventByEventType(event);
        List<FileEventDto> fileEventDtos = new ArrayList<>();
        for(FileEvent fileEvent : fileEvents){
            fileEventDtos.add(map.entityToDto(fileEvent));
        }
        return fileEventDtos;
    }

    public List<FileEventDto> findAllEventByDay(String day){
        List<FileEvent> fileEvents = fileEventRepo.findAll();
        List<FileEventDto> fileEventDtos = new ArrayList<>();
        for(FileEvent fileEvent : fileEvents){
            if(checkDay(fileEvent.getEventDate().toString(),day)){
                fileEventDtos.add(map.entityToDto(fileEvent));
            }
        }
        return fileEventDtos;
    }
    private boolean checkDay(String day, String dayToCheck){
        if(day.substring(8,10).equals(dayToCheck)){
            return true;
        }else {
            return false;
        }
    }
    public List<FileEventDto> findAllEventByMonth(String month){
        List<FileEvent> fileEvents = fileEventRepo.findAll();
        List<FileEventDto> fileEventDtos = new ArrayList<>();
        for(FileEvent fileEvent : fileEvents){
            if(checkMonth(fileEvent.getEventDate().toString(),month)){
                fileEventDtos.add(map.entityToDto(fileEvent));
            }
        }
        return fileEventDtos;
    }
    private boolean checkMonth(String month, String monthToCheck){
        if(month.substring(5,7).equals(monthToCheck)){
            return true;
        }else {
            return false;
        }
    }
    public List<FileEventDto> findAllEventByYear(String year){
        List<FileEvent> fileEvents = fileEventRepo.findAll();
        List<FileEventDto> fileEventDtos = new ArrayList<>();
        for(FileEvent fileEvent : fileEvents){
            if(checkYear(fileEvent.getEventDate().toString(),year)){
                fileEventDtos.add(map.entityToDto(fileEvent));
            }
        }
        return fileEventDtos;
    }
    private boolean checkYear(String year, String yearToCheck){
        if(year.substring(0,4).equals(yearToCheck)){
            return true;
        }else {
            return false;
        }
    }
    public List<FileEventDto> getAllEventByFileId(Long id) {
        List<FileEvent> fileEvents = fileEventRepo.findAllEventByFileId(id);
        List<FileEventDto> fileEventDtos = new ArrayList<>();
        for(FileEvent fileEvent : fileEvents){
            fileEventDtos.add(map.entityToDto(fileEvent));
        }
        return fileEventDtos;

    }
}
