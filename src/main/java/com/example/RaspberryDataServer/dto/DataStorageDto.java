package com.example.RaspberryDataServer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class DataStorageDto {

    private Long id;
    private String name;
    private String format;
    private String path;

    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private Date date;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private Date dateLatestChange;
    private boolean deleted;

    public DataStorageDto(Long id, String name, String format, String path, Date date, Date dateLatestChange, boolean deleted) {
        this.id = id;
        this.name = name;
        this.format = format;
        this.path = path;
        this.date = date;
        this.dateLatestChange = dateLatestChange;
        this.deleted = deleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDateLatestChange() {
        return dateLatestChange;
    }

    public void setDateLatestChange(Date dateLatestChange) {
        this.dateLatestChange = dateLatestChange;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
