package com.example.RaspberryDataServer.models.entities;

import com.example.RaspberryDataServer.utility.enums.FileEventEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class SystemUpdateEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String systemVersion;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private Date eventDate;
    private FileEventEnum eventType;

    public SystemUpdateEvent(String systemVersion, Date eventDate, FileEventEnum eventType) {
        this.systemVersion = systemVersion;
        this.eventDate = eventDate;
        this.eventType = eventType;
    }

    public SystemUpdateEvent() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
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
