package com.example.RaspberryDataServer.mappers;

import com.example.RaspberryDataServer.models.dto.FileEventDto;
import com.example.RaspberryDataServer.models.entities.FileEvent;
import com.example.RaspberryDataServer.repositories.StoredFileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileEventMapper {
    @Autowired
    private StoredFileRepo storedFileRepo;


    public FileEventDto entityToDto(FileEvent ent){
        return new FileEventDto(
                ent.getFile().getId(),
                ent.getFileName(),
                ent.getIp(),
                ent.getEventDate(),
                ent.getEventType(),
                ent.getEventDescription()
        );
    }
    public FileEvent dtoToEntity(FileEventDto dto){
        return new FileEvent(
                storedFileRepo.findById(dto.getFile_id()).get(),
                dto.getFileName(),
                dto.getIp(),
                dto.getEventDate(),
                dto.getEventType(),
                dto.getEventDescription()
        );
    }
}
