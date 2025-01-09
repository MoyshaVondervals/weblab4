package org.moysha.lab4t.repository;

import org.moysha.lab4t.models.PointEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<PointEntity, Integer> {
    @Query(value = "SELECT * FROM POINTS WHERE AUTHOR = :id ORDER BY id DESC FETCH FIRST 10 ROWS ONLY", nativeQuery = true)
    List<PointEntity> find10PointsByUserId(@Param("id") Integer id);


}