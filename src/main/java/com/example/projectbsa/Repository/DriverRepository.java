package com.example.projectbsa.Repository;

import com.example.projectbsa.Entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Long> {
    @Query(value="Select * from Driver d where d.id_driver=?1",nativeQuery = true)
    List<Driver> findDriverbyId(long id);

    @Query(value="Select * from Driver d where d.name=?1",nativeQuery = true)
    List<Driver> findDriverbyName(String name);
}
