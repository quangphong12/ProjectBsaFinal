package com.example.projectbsa.Repository;

import com.example.projectbsa.Entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TravelRepository extends JpaRepository <Travel,Integer>{
    @Query(value="SELECT t.id_bus FROM Travel t WHERE t.travel_time=?1 AND t.place=?2 ",nativeQuery = true)
    Long findIdBus(String travel,String place);

    @Query(value="SELECT t.id_Travel FROM Travel t WHERE t.travel_time=?1 AND t.place=?2",nativeQuery = true)
    Integer findIdTravel(String travel,String place);

    @Query(value="Select * from Travel t where t.id_Travel=?1",nativeQuery = true)
    List<Travel> findTravelbyId(int id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value="UPDATE Travel SET seat_ordered =?1 WHERE id_travel = ?2",nativeQuery = true)
    void updateOrderedSeat(int seats, int id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value="UPDATE Travel SET seat_empty = b.seats - seat_ordered FROM Travel join Bus b on Travel.id_bus=b.id_bus WHERE Travel.id_travel = ?1",nativeQuery = true)
    void updateEmptySeat(int id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value="UPDATE Travel SET seat_ordered =0 WHERE id_travel>=100",nativeQuery = true)
    void resetOrderedSeat();

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value="UPDATE Travel SET seat_empty = b.seats FROM Travel join Bus b on Travel.id_bus=b.id_bus WHERE Travel.id_travel>=100",nativeQuery = true)
    void resetEmptySeat();

    @Query(value="Select * from Travel t where t.id_Travel=?1",nativeQuery = true)
    Travel findPlaceAndTimeById(int id);

    @Query(value="Select * from Travel t where t.id_bus=?1",nativeQuery = true)
    List<Travel> findTravelbyIdBus(long id);
}
