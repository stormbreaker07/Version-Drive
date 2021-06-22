package com.example.versionDriver.repositories;

import com.example.versionDriver.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegisterUser extends JpaRepository<UserEntity , Long> {

    /**
     * write query to find user by email id
     */
    @Query("SELECT u FROM UserEntity u WHERE u.email = ?1")
    Optional<UserEntity> findUserByEmail(String email);

    @Query("SELECT u FROM UserEntity u WHERE u.email = ?1 AND u.password = ?2")
    public Optional<UserEntity> findUserByEmailAndPassword(String email , String password);

}
