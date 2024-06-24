package com.example.RaspberryDataServer.mappers;

import com.example.RaspberryDataServer.dto.DataStorageDto;
import com.example.RaspberryDataServer.entities.DataStorage;
import org.springframework.stereotype.Component;

@Component
public class DataStorageMapper {

    public DataStorageDto entityToDto(DataStorage ent){
        return new DataStorageDto(
                ent.getId(),
                ent.getName(),
                ent.getFormat(),
                ent.getPath(),
                ent.getDate(),
                ent.getDateLatestChange(),
                ent.isDeleted()
        );
    }

    public DataStorage dtoToEntity(DataStorageDto dto){
        return new DataStorage(
                dto.getName(),
                dto.getFormat(),
                dto.getPath(),
                dto.getDate(),
                dto.getDateLatestChange()
        );
    }
}
