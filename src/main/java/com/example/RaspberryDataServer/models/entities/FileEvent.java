package com.example.RaspberryDataServer.models.entities;

import com.example.RaspberryDataServer.utility.enums.FileEventEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class FileEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private StoredFile file;
    private String fileName;
    private String ip;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private Date eventDate;
    private FileEventEnum eventType;
    private String eventDescription;

    public FileEvent(StoredFile file,String fileName, String ip, Date eventDate) {
        this.file = file;
        this.fileName = fileName;
        this.ip = ip;
        this.eventDate = eventDate;
        this.eventType = FileEventEnum.CREATION;
        this.eventDescription = "File Created";
    }

    public FileEvent(StoredFile file,String fileName, String ip, Date eventDate, FileEventEnum eventType, String eventDescription) {
        this.file = file;
        this.fileName = fileName;
        this.ip = ip;
        this.eventDate = eventDate;
        this.eventType = eventType;
        this.eventDescription = eventDescription;
    }

    public FileEvent() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StoredFile getFile() {
        return file;
    }

    public void setFile(StoredFile file) {
        this.file = file;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
