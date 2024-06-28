package com.example.RaspberryDataServer.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table
public class StoredFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String format;
    private String path;

    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private Date date;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private Date dateLatestChange;
    private boolean deleted;


    public StoredFile(String name, String format, String path, Date date, Date dateLatestChange) {
        this.name = name;
        this.format = format;
        this.path = path;
        this.date = date;
        this.dateLatestChange = dateLatestChange;
        this.deleted = false;
    }

    public StoredFile() {
    }


    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getDateLatestChange() {
        return dateLatestChange;
    }

    public void setDateLatestChange(Date dateLatestChange) {
        this.dateLatestChange = dateLatestChange;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
