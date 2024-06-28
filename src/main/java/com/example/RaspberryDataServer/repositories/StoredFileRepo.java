package com.example.RaspberryDataServer.repositories;

import com.example.RaspberryDataServer.models.entities.StoredFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoredFileRepo extends JpaRepository<StoredFile, Long> {
    @Query("SELECT d FROM StoredFile d WHERE d.name = :name AND d.deleted = false")
    Optional<StoredFile> findFileByName(@Param("name") String name);

    @Query("SELECT d FROM StoredFile d WHERE d.deleted = false")
    List<StoredFile> findAllFilesNotDeleted();

    @Query("SELECT d FROM StoredFile d WHERE d.deleted = true")
    List<StoredFile> findAllDeletedfiles();

    boolean existsByName(String name);
}
