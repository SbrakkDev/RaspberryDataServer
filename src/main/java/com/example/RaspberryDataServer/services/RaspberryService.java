package com.example.RaspberryDataServer.services;

import com.example.RaspberryDataServer.mappers.StoredFileMapper;
import com.example.RaspberryDataServer.mappers.SystemUpdateEventMapper;
import com.example.RaspberryDataServer.models.dto.StoredFileDto;
import com.example.RaspberryDataServer.models.dto.SystemUpdateEventDto;
import com.example.RaspberryDataServer.models.entities.StoredFile;
import com.example.RaspberryDataServer.models.entities.SystemUpdateEvent;
import com.example.RaspberryDataServer.exceptions.SystemException;
import com.example.RaspberryDataServer.repositories.FileEventRepo;
import com.example.RaspberryDataServer.repositories.StoredFileRepo;
import com.example.RaspberryDataServer.repositories.SystemUpdateEventRepo;
import com.example.RaspberryDataServer.utility.enums.FileEventEnum;
import com.example.RaspberryDataServer.utility.messages.Codes;
import com.example.RaspberryDataServer.utility.messages.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RaspberryService {

    @Autowired
    private StoredFileRepo storedFileRepo;
    @Autowired
    private FileEventRepo fileEventRepo;
    @Autowired
    private SystemUpdateEventRepo systemUpdateEventRepo;
    @Autowired
    private SystemUpdateEventMapper systemMap;
    @Autowired
    private StoredFileMapper fileMap;

    @Value("${fileRepoFolder}")
    private String fileRepoFolder;

    public void updateApp(MultipartFile file) throws IOException, SystemException {
        File finalDest = new File(fileRepoFolder + "/app/RaspberryServerApp.jar");
        if(!systemUpdateEventRepo.existsSystemUpdateEventBySystemVersion(file.getOriginalFilename())){
            if(finalDest.exists()){
                finalDest.delete();
            }
            SystemUpdateEvent newUpdate = new SystemUpdateEvent(file.getOriginalFilename(),new Date(), FileEventEnum.SYSTEM_UPDATE);
            systemUpdateEventRepo.save(newUpdate);
            file.transferTo(finalDest);
        }else{
            throw new SystemException(ErrorMessage.SYSTEM_UP_TO_DATE, Codes.IGNORED_CODE);
        }

    }
    public void cleanDb(){
        fileEventRepo.deleteAll();
        storedFileRepo.deleteAll();
    }

    public List<SystemUpdateEventDto> getAllUpdate(){
        List<SystemUpdateEvent> systemUpdateEvents = systemUpdateEventRepo.findAll();
        List<SystemUpdateEventDto> systemUpdateEventDtos = new ArrayList<>();
        for(SystemUpdateEvent event : systemUpdateEvents){
            systemUpdateEventDtos.add(systemMap.entityToDto(event));
        }
        return systemUpdateEventDtos;
    }

    public List<StoredFileDto> getAllDeletedFiles() {
        List<StoredFile> storedFileList = storedFileRepo.findAllDeletedfiles();
        List<StoredFileDto> storedFileDtoList = new ArrayList<>();
        for(StoredFile storedFile : storedFileList){
            storedFileDtoList.add(fileMap.entityToDto(storedFile));
        }
        return storedFileDtoList;
    }

}
