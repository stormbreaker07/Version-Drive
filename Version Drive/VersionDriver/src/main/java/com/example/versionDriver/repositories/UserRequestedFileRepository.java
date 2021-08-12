package com.example.versionDriver.repositories;

import com.example.versionDriver.entities.UserRequestedFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

public interface UserRequestedFileRepository extends JpaRepository<UserRequestedFile , Long> {

    @Query("SELECT W FROM UserRequestedFile W WHERE W.ownerId = ?1 OR W.userId = ?2")
    Optional<List<UserRequestedFile>> findFilesByownerId(Long ownerId, Long userId);

    @Query("SELECT W FROM UserRequestedFile W WHERE W.userId = ?1")
    List<UserRequestedFile> findFilesByUserId(Long userId);

    @Query("SELECT W FROM UserRequestedFile W WHERE W.fileId = ?1 AND W.userId = ?2")
    UserRequestedFile findByFileIDAndUserId(Long fileId , Long userId);

    @Query("DELETE FROM UserRequestedFile W WHERE W.fileId = ?1")
    void deleteFilesByFileId(Long fileId);

    @Query("SELECT W FROM UserRequestedFile W WHERE W.fileId = ?1")
    List<UserRequestedFile> findByFileID(Long fileId);
}
