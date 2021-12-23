package com.example.projectbsa.Service.Impl;

import com.example.projectbsa.Entity.Driver;
import com.example.projectbsa.Repository.DriverRepository;
import com.example.projectbsa.Service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {
    @Autowired
    DriverRepository driverRepository;
    @Override
    public Optional<Driver> findDriverById(long id) {
        return driverRepository.findById(id);
    }

    @Override
    public List<Driver> findAll() {
        return (List<Driver>) driverRepository.findAll();
    }

    @Override
    public void saveDriver(Driver driver) {
        driverRepository.save(driver);
    }

    @Override
    public void deleteDriver(long id) {
        driverRepository.deleteById(id);
    }

    @Override
    public List<Driver> findDriverbyId(long id) {
        return (List<Driver>) driverRepository.findDriverbyId(id);
    }

    @Override
    public List<Driver> findDriverbyName(String name) {
        return (List<Driver>) driverRepository.findDriverbyName(name);
    }
}
