package com.example.RaspberryDataServer.mappers;

import com.example.RaspberryDataServer.models.dto.StoredFileDto;
import com.example.RaspberryDataServer.models.dto.SystemUpdateEventDto;
import com.example.RaspberryDataServer.models.entities.StoredFile;
import com.example.RaspberryDataServer.models.entities.SystemUpdateEvent;
import org.springframework.stereotype.Component;

@Component
public class SystemUpdateEventMapper {

    public SystemUpdateEventDto entityToDto(SystemUpdateEvent ent){
        return new SystemUpdateEventDto(
                ent.getSystemVersion(),
                ent.getEventDate(),
                ent.getEventType()
        );
    }

    public SystemUpdateEvent dtoToEntity(SystemUpdateEventDto dto){
        return new SystemUpdateEvent(
                dto.getSystemVersion(),
                dto.getEventDate(),
                dto.getEventType()
        );
    }

}
