package com.example.projectbsa.Service.Impl;

import com.example.projectbsa.Entity.Bus;
import com.example.projectbsa.Repository.BusRepository;
import com.example.projectbsa.Service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Access;
import java.util.List;
import java.util.Optional;

@Service
public class BusServiceImpl implements BusService {
    @Autowired
    BusRepository busRepository;
    @Override
    public Long findIdDriver(long id) {
        return busRepository.findIdDriver(id);
    }

    @Override
    public Long findIdBus(String licensePlateBus) {
        return busRepository.findIdBus(licensePlateBus);
    }

    @Override
    public Optional<Bus> findBusById(long id) {
        return busRepository.findById(id);
    }

    @Override
    public List<Bus> findAll() {
        return (List<Bus>) busRepository.findAll();
    }

    @Override
    public void saveBus(Bus bus) {
        busRepository.save(bus);
    }

    @Override
    public void deleteBus(long id) {
        busRepository.deleteById(id);
    }

    @Override
    public List<Bus> findBusbyId(long id) {
        return (List<Bus>) busRepository.findBusbyId(id);
    }
}
