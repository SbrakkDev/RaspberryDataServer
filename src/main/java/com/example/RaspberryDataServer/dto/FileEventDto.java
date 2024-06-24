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
    private FileEventEnum eventTYpe;

    public FileEventDto(Long id, String file_name, String ip, Date eventDate, FileEventEnum eventTYpe) {
        this.id = id;
        this.file_name = file_name;
        this.ip = ip;
        this.eventDate = eventDate;
        this.eventTYpe = eventTYpe;
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

    public FileEventEnum getEventTYpe() {
        return eventTYpe;
    }

    public void setEventTYpe(FileEventEnum eventTYpe) {
        this.eventTYpe = eventTYpe;
    }
}
