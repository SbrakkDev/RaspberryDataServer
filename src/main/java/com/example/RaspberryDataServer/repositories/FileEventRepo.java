package com.example.RaspberryDataServer.repositories;

import com.example.RaspberryDataServer.entities.FileEvent;
import com.example.RaspberryDataServer.enums.FileEventEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileEventRepo extends JpaRepository<FileEvent, Long> {

    @Query("SELECT e FROM  FileEvent e WHERE e.file.name = :name AND e.file.deleted = false")
    List<FileEvent> findAllEventByFileName (@Param("name") String name);

    @Query("SELECT e FROM  FileEvent e WHERE e.ip = :ip")
    List<FileEvent> findAllEventByIp (@Param("ip") String ip);

    @Query("SELECT e FROM  FileEvent e WHERE e.eventType = :event")
    List<FileEvent> findAllEventByEventType (@Param("event") FileEventEnum event);

    @Query("SELECT e FROM  FileEvent e WHERE e.file.id = :id")
    List<FileEvent> findAllEventByFileId (@Param("id") Long id);
}
