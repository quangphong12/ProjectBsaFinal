package com.example.projectbsa.Repository;

import com.example.projectbsa.Entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus,Long> {
    @Query(value="Select b.id_driver From Bus b where b.id_bus=?1",nativeQuery = true)
    Long findIdDriver(long id);
    @Query(value="Select * from Bus b where b.id_bus=?1",nativeQuery = true)
    List<Bus> findBusbyId(long id);
    @Query(value="Select b.id_bus From Bus b where b.license_plate_bus=?1",nativeQuery = true)
    Long findIdBus(String licensePlateBus);
}
