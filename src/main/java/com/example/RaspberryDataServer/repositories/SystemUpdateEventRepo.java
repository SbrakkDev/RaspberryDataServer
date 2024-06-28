package com.example.RaspberryDataServer.repositories;

import com.example.RaspberryDataServer.models.entities.SystemUpdateEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemUpdateEventRepo extends JpaRepository<SystemUpdateEvent, Long> {

    boolean existsSystemUpdateEventBySystemVersion(String version);
}
