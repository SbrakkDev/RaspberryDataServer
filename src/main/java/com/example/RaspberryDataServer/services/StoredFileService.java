package com.example.RaspberryDataServer.services;

import com.example.RaspberryDataServer.dto.StoredFileDto;
import com.example.RaspberryDataServer.entities.FileEvent;
import com.example.RaspberryDataServer.entities.StoredFile;
import com.example.RaspberryDataServer.enums.FileEventEnum;
import com.example.RaspberryDataServer.mappers.StoredFileMapper;
import com.example.RaspberryDataServer.repositories.FileEventRepo;
import com.example.RaspberryDataServer.repositories.StoredFileRepo;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StoredFileService {

    @Autowired
    private StoredFileRepo storedFileRepo;
    @Autowired
    private FileEventRepo fileEventRepo;
    @Autowired
    private StoredFileMapper map;

    @Value("${fileRepoFolder}")
    private String fileRepoFolder;
    public String upload(MultipartFile file, HttpServletRequest request) throws IOException {
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        String newFileName = checkDuplicateFile(file.getOriginalFilename());
        File filePath = new File(fileRepoFolder + "/" + newFileName);
        file.transferTo(filePath);
        String ip = request.getRemoteAddr();
        StoredFile newData = new StoredFile(newFileName,ext,fileRepoFolder,new Date(),new Date());
        FileEvent newEvent = new FileEvent(newData,ip,new Date());
        storedFileRepo.save(newData);
        fileEventRepo.save(newEvent);
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

    public List<StoredFileDto> getAllFiles() {
        List<StoredFile> storedFileList = storedFileRepo.findAll();
        List<StoredFileDto> storedFileDtoList = new ArrayList<>();
        for(StoredFile storedFile : storedFileList){
            storedFileDtoList.add(map.entityToDto(storedFile));
        }
        return storedFileDtoList;
    }
    public List<StoredFileDto> getAllFilesNotDeleted() {
        List<StoredFile> storedFileList = storedFileRepo.findAllFilesNotDeleted();
        List<StoredFileDto> storedFileDtoList = new ArrayList<>();
        for(StoredFile storedFile : storedFileList){
            storedFileDtoList.add(map.entityToDto(storedFile));
        }
        return storedFileDtoList;
    }

    public String deleteFile(String fileName,HttpServletRequest request) {
        String response = "";
        String ip = request.getRemoteAddr();
        Optional<StoredFile> storedFile = storedFileRepo.findFileByName(fileName);
        if (storedFile.isPresent()&&!storedFile.get().getDeleted()) {
            storedFile.get().setDeleted(true);
            FileEvent newEvent = new FileEvent(storedFile.get(),ip,new Date(), FileEventEnum.DELETE);
            storedFileRepo.saveAndFlush(storedFile.get());
            fileEventRepo.save(newEvent);
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
        fileEventRepo.deleteAll();
        storedFileRepo.deleteAll();
   }

}
