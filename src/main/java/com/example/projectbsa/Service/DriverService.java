package com.example.projectbsa.Service;

import com.example.projectbsa.Entity.Bus;
import com.example.projectbsa.Entity.Driver;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DriverService {
    Optional<Driver> findDriverById(long id);

    List<Driver> findAll();

    void saveDriver(Driver driver);

    void deleteDriver(long id);

    ///
    List<Driver> findDriverbyId(long id);

    List<Driver> findDriverbyName(String name);
}
