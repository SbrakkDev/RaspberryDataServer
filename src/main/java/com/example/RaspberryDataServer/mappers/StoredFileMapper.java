package com.example.RaspberryDataServer.mappers;

import com.example.RaspberryDataServer.dto.StoredFileDto;
import com.example.RaspberryDataServer.entities.StoredFile;
import org.springframework.stereotype.Component;

@Component
public class StoredFileMapper {

    public StoredFileDto entityToDto(StoredFile ent){
        return new StoredFileDto(
                ent.getId(),
                ent.getName(),
                ent.getFormat(),
                ent.getPath(),
                ent.getDate(),
                ent.getDateLatestChange(),
                ent.getDeleted()
        );
    }

    public StoredFile dtoToEntity(StoredFileDto dto){
        return new StoredFile(
                dto.getName(),
                dto.getFormat(),
                dto.getPath(),
                dto.getDate(),
                dto.getDateLatestChange()
        );
    }
}
