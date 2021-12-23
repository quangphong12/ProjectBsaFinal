package com.example.projectbsa.Service;


import com.example.projectbsa.Entity.Travel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TravelService {
    Optional<Travel> findTravelById(int id);

    List<Travel> findAll();

    void saveTravel(Travel time);

    void deleteTravel(int id);

    ///
    Long findIdBus(String time, String place);

    Integer findIdTravel(String travel, String place);

    List<Travel> findTravelbyId(int id);

    void updateOrderedSeat(int seats, int id);

    void updateEmptySeat(int id);

    void resetOrderedSeat();

    void resetEmptySeat();

    Travel findPlaceAndTimeById(int id);

    List<Travel> findTravelbyIdBus(long id);
}
