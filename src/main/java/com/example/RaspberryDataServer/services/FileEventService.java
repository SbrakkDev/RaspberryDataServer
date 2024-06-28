package com.example.RaspberryDataServer.services;

import com.example.RaspberryDataServer.models.dto.FileEventDto;
import com.example.RaspberryDataServer.models.entities.FileEvent;
import com.example.RaspberryDataServer.utility.enums.FileEventEnum;
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

    public List<FileEventDto> findAllEventByDay(String day,String month,String year){
        List<FileEvent> fileEvents = fileEventRepo.findAll();
        List<FileEventDto> fileEventDtos = new ArrayList<>();
        for(FileEvent fileEvent : fileEvents){
            if(checkDay(fileEvent.getEventDate().toString(),day, month,year)){
                fileEventDtos.add(map.entityToDto(fileEvent));
            }
        }
        return fileEventDtos;
    }
    private boolean checkDay(String date, String day, String month,String year){
        String dateToCheck = year+"-"+month+"-"+day;
        if(date.substring(0,10).equals(dateToCheck)){
            return true;
        }else {
            return false;
        }
    }
    public List<FileEventDto> findAllEventByMonth(String month,String year){
        List<FileEvent> fileEvents = fileEventRepo.findAll();
        List<FileEventDto> fileEventDtos = new ArrayList<>();
        for(FileEvent fileEvent : fileEvents){
            if(checkMonth(fileEvent.getEventDate().toString(),month,year)){
                fileEventDtos.add(map.entityToDto(fileEvent));
            }
        }
        return fileEventDtos;
    }
    private boolean checkMonth(String date, String month, String year){
        String dateToCheck = year+"-"+month;
        if(date.substring(0,7).equals(dateToCheck)){
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
