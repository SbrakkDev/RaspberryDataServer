package com.example.RaspberryDataServer.models.dto;

import com.example.RaspberryDataServer.utility.enums.FileEventEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class FileEventDto {

    private Long file_id;
    private String fileName;
    private String ip;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private Date eventDate;
    private FileEventEnum eventType;
    private String eventDescription;

    public FileEventDto(Long file_id,String fileName, String ip, Date eventDate, FileEventEnum eventType,String eventDescription) {
        this.file_id = file_id;
        this.fileName = fileName;
        this.ip = ip;
        this.eventDate = eventDate;
        this.eventType = eventType;
        this.eventDescription = eventDescription;
    }

    public FileEventDto() {
    }

    public Long getFile_id() {
        return file_id;
    }

    public void setFile_id(Long file_id) {
        this.file_id = file_id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public FileEventEnum getEventType() {
        return eventType;
    }

    public void setEventType(FileEventEnum eventType) {
        this.eventType = eventType;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }
}
