package com.example.RaspberryDataServer.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class DataStorage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String format;
    private String path;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private Date date;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private Date dateLatestChange;

    public DataStorage(String name, String format, String path, Date date, Date dateLatestChange) {
        this.name = name;
        this.format = format;
        this.path = path;
        this.date = date;
        this.dateLatestChange = dateLatestChange;
    }

    public DataStorage() {
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
