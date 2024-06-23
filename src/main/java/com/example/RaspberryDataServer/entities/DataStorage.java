package com.example.RaspberryDataServer.entities;

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
    private Date date;

    public DataStorage(String name, String format, Date date) {
        this.name = name;
        this.format = format;
        this.date = date;
    }

    public DataStorage() {
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
