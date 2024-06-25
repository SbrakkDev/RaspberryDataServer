package com.example.RaspberryDataServer.dto;

import com.example.RaspberryDataServer.enums.FileEventEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class FileEventDto {
    private Long id;
    private String file_name;
    private String ip;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private Date eventDate;
    private FileEventEnum eventType;
    private String eventDescription;

    public FileEventDto(Long id, String file_name, String ip, Date eventDate, FileEventEnum eventType,String eventDescription) {
        this.id = id;
        this.file_name = file_name;
        this.ip = ip;
        this.eventDate = eventDate;
        this.eventType = eventType;
        this.eventDescription = eventDescription;
    }

    public FileEventDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
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
