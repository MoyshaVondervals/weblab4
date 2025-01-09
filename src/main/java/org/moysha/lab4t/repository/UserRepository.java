package org.moysha.lab4t.repository;

import org.moysha.lab4t.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findByUsername(String username);
    @Query("SELECT u.id FROM AppUser u WHERE u.username = :username")
    Integer findIdByUsername(@Param("username") String username);
}