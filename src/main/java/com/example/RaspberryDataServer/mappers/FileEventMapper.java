package com.example.RaspberryDataServer.mappers;

import com.example.RaspberryDataServer.dto.FileEventDto;
import com.example.RaspberryDataServer.entities.FileEvent;
import com.example.RaspberryDataServer.repositories.StoredFileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileEventMapper {
    @Autowired
    private StoredFileRepo storedFileRepo;


    public FileEventDto entityToDto(FileEvent ent){
        return new FileEventDto(
                ent.getId(),
                ent.getFile().getName(),
                ent.getIp(),
                ent.getEventDate(),
                ent.getEventType(),
                ent.getEventDescription()
        );
    }
    public FileEvent dtoToEntity(FileEventDto dto){
        return new FileEvent(
                storedFileRepo.findFileByName(dto.getFile_name()).get(),
                dto.getIp(),
                dto.getEventDate(),
                dto.getEventType(),
                dto.getEventDescription()
        );
    }
}
