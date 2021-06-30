package com.example.versionDriver.repositories;

import com.example.versionDriver.entities.UserRequestedFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.Optional;

public interface UserRequestedFileRepository extends JpaRepository<UserRequestedFile , Long> {


    @Query("SELECT W FROM UserRequestedFile W WHERE W.ownerId = ?1")
    Optional<ArrayList<UserRequestedFile>> findFilesByownerId(String owner_id);
}
