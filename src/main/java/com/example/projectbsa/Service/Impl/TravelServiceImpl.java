package com.example.projectbsa.Service.Impl;

import com.example.projectbsa.Entity.Travel;
import com.example.projectbsa.Repository.TravelRepository;
import com.example.projectbsa.Service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TravelServiceImpl implements TravelService {
    @Autowired
    TravelRepository travelRepository;

    @Override
    public Long findIdBus(String time, String place) {
        return travelRepository.findIdBus(time,place);
    }

    @Override
    public Integer findIdTravel(String travel, String place) {
        return travelRepository.findIdTravel(travel,place);
    }

    @Override
    public Optional<Travel> findTravelById(int id) {
        return travelRepository.findById(id);
    }

    @Override
    public List<Travel> findAll() {
        return (List<Travel>) travelRepository.findAll();
    }

    @Override
    public void saveTravel(Travel travel) {
        travelRepository.save(travel);
    }

    @Override
    public void deleteTravel(int id) {
        travelRepository.deleteById(id);
    }

    @Override
    public List<Travel> findTravelbyId(int id){
        return (List<Travel>) travelRepository.findTravelbyId( id);
    }

    @Override
    public void updateOrderedSeat(int seats, int id) {
        travelRepository.updateOrderedSeat(seats,id);
    }

    @Override
    public void updateEmptySeat(int id) {
        travelRepository.updateEmptySeat(id);
    }

    @Override
    public void resetOrderedSeat() {
        travelRepository.resetOrderedSeat();
    }

    @Override
    public void resetEmptySeat() {
        travelRepository.resetEmptySeat();
    }

    @Override
    public Travel findPlaceAndTimeById(int id) {
        return travelRepository.findPlaceAndTimeById(id);
    }

    @Override
    public List<Travel> findTravelbyIdBus(long id) {
        return (List<Travel>) travelRepository.findTravelbyIdBus(id);
    }
}
