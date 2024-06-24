package com.example.RaspberryDataServer.services;

import com.example.RaspberryDataServer.dto.DataStorageDto;
import com.example.RaspberryDataServer.entities.DataStorage;
import com.example.RaspberryDataServer.mappers.DataStorageMapper;
import com.example.RaspberryDataServer.repositories.DataStorageRepo;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class DataStorageService {

    @Autowired
    private DataStorageRepo dataStorageRepo;
    @Autowired
    private DataStorageMapper map;

    @Value("${fileRepoFolder}")
    private String fileRepoFolder;
    public String upload(MultipartFile file) throws IOException {
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        String newFileName = checkDuplicateFile(file.getOriginalFilename());

        File finalDest = new File(fileRepoFolder + "/" + newFileName);

        file.transferTo(finalDest);
        String path = fileRepoFolder;
        DataStorage newData = new DataStorage(newFileName,ext,path,new Date(),new Date());
        dataStorageRepo.save(newData);

        return newFileName;
    }
    public String uploadNewJar(MultipartFile file) throws IOException {
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        File finalDest = new File(fileRepoFolder + "/" + file.getOriginalFilename());

        if(finalDest.exists()){
            finalDest.delete();
        }
        file.transferTo(finalDest);

        return file.getOriginalFilename();
    }
    public String checkDuplicateFile(String fileName){
        int i = 1;
        String numberExt = "";
        File file = new File(fileRepoFolder + "/" + fileName);
        while (file.exists()){
            numberExt = "(" + i + ")";
            if(!new File(file + numberExt).exists()){
                file = new File(file +numberExt);
            }
            i++;
        }
        fileName = fileName + numberExt;
        return fileName;
    }



    public byte[] download(String file) throws IOException {
        File fileFromRepo = new File(fileRepoFolder + "/"+file);
        if(!fileFromRepo.exists()) throw new IOException("File does not exist");
        return IOUtils.toByteArray(new FileInputStream(fileFromRepo));
    }

    public List<DataStorageDto> getAllFiles() {
        List<DataStorage> dataStorageList = dataStorageRepo.findAll();
        List<DataStorageDto> dataStorageDtoList = new ArrayList<>();
        for(DataStorage dataStorage : dataStorageList){
            dataStorageDtoList.add(map.entityToDto(dataStorage));
        }
        return dataStorageDtoList;
    }

    public String deleteFile(String fileName) {
        String response = "";
        if (dataStorageRepo.existsByName(fileName)) {
            dataStorageRepo.delete(dataStorageRepo.findFileByName(fileName));
            response = response + "file eliminated from db";
        }else{
            return "no file was found";
        }
        File file = new File(fileRepoFolder + "/" + fileName);
        if(file.exists()) {
            file.delete();
            response = response + " and from the data storage";
        }else{
            response = response + " but no file was found in the data storage";
        }
        return response;
    }
    public void cleanDb(){
        dataStorageRepo.deleteAll();
    }
}
