package com.example.versionDriver.repositories;

import com.example.versionDriver.entities.UploadedFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadedFilerepository extends JpaRepository<UploadedFile , Long> {


}
