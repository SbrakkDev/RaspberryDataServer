package com.example.RaspberryDataServer.models.dto;

import com.example.RaspberryDataServer.utility.enums.FileEventEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SystemUpdateEventDto {
    private String systemVersion;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private Date eventDate;
    private FileEventEnum eventType;

    public SystemUpdateEventDto(String systemVersion, Date eventDate, FileEventEnum eventType) {
        this.systemVersion = systemVersion;
        this.eventDate = eventDate;
        this.eventType = eventType;
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
