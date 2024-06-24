package com.example.RaspberryDataServer.repositories;

import com.example.RaspberryDataServer.entities.StoredFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoredFileRepo extends JpaRepository<StoredFile, Long> {
    @Query("SELECT d FROM StoredFile d WHERE d.name = :name")
    Optional<StoredFile> findFileByName(@Param("name") String name);

    @Query("SELECT d FROM StoredFile d WHERE d.deleted = false")
    List<StoredFile> findAllFilesNotDeleted();
    boolean existsByName(String name);
}
