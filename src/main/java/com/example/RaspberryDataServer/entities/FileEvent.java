package com.example.RaspberryDataServer.entities;

import com.example.RaspberryDataServer.enums.FileEventEnum;
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
    private String ip;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private Date eventDate;
    private FileEventEnum eventType;

    public FileEvent(StoredFile file, String ip, Date eventDate) {
        this.file = file;
        this.ip = ip;
        this.eventDate = eventDate;
        this.eventType = FileEventEnum.CREATION;
    }

    public FileEvent(StoredFile file, String ip, Date eventDate, FileEventEnum eventType) {
        this.file = file;
        this.ip = ip;
        this.eventDate = eventDate;
        this.eventType = eventType;
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
}
