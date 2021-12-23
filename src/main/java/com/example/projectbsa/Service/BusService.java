package com.example.projectbsa.Service;

import com.example.projectbsa.Entity.Bus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BusService {
    Optional<Bus> findBusById(long id);

    List<Bus> findAll();

    void saveBus(Bus bus);

    void deleteBus(long id);

    ///
    List<Bus> findBusbyId(long id);

    Long findIdDriver(long id);

    Long findIdBus(String licensePlateBus);
}
