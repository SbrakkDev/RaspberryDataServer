package com.example.RaspberryDataServer.repositories;

import com.example.RaspberryDataServer.entities.FileEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileEventRepo extends JpaRepository<FileEvent, Long> {
}
