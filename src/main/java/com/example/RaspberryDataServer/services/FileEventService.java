package com.example.RaspberryDataServer.services;

import com.example.RaspberryDataServer.dto.FileEventDto;
import com.example.RaspberryDataServer.entities.FileEvent;
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
}
