package com.example.RaspberryDataServer.services;

import com.example.RaspberryDataServer.entities.DataStorage;
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
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class DataStorageService {

    @Autowired
    private DataStorageRepo dataStorageRepo;

    @Value("${fileRepoFolder}")
    private String fileRepoFolder;
    public String upload(MultipartFile file) throws IOException {
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        String newFileName = file.getOriginalFilename();
        File finalDest = new File(fileRepoFolder + "/" + newFileName);

        finalDest = checkDuplicateFile(finalDest);
        file.transferTo(finalDest);

        DataStorage newData = new DataStorage(newFileName,ext,finalDest.toString(),new Date(),new Date());
        dataStorageRepo.save(newData);

        return newFileName;
    }
    public String uploadNewJar(MultipartFile file) throws IOException {
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        String newFileName = file.getOriginalFilename();
        File finalDest = new File(fileRepoFolder + "/" + newFileName);

        if(finalDest.exists()){
            finalDest.delete();
        }
        file.transferTo(finalDest);

        DataStorage newData = new DataStorage(newFileName,ext,finalDest.toString(),new Date(),new Date());
        dataStorageRepo.save(newData);

        return newFileName;
    }

    public File checkDuplicateFile(File file){
        int i = 1;
        String path = file.toString();
        while (file.exists()){
            if(!new File(path +"(" + i + ")").exists()){
                file = new File(path +"(" + i + ")");
            }
            i++;
        }
        return file;
    }

    public byte[] download(String file) throws IOException {
        File fileFromRepo = new File(fileRepoFolder + "\\"+file);
        if(!fileFromRepo.exists()) throw new IOException("File does not exist");
        return IOUtils.toByteArray(new FileInputStream(fileFromRepo));
    }

    public List<DataStorage> getAllFiles() {
        return dataStorageRepo.findAll();
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
