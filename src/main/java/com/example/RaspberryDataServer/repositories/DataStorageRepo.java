package com.example.RaspberryDataServer.repositories;

import com.example.RaspberryDataServer.entities.DataStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataStorageRepo extends JpaRepository<DataStorage, Long> {
    @Query("SELECT d FROM DataStorage d WHERE d.name = :name")
    DataStorage findFileByName(@Param("name") String name);

    boolean existsByName(String name);
}
