package com.example.projectbsa.Service.Impl;

import com.example.projectbsa.Entity.Rent;
import com.example.projectbsa.Repository.RentRepository;
import com.example.projectbsa.Service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentServiceImpl implements RentService {
    @Autowired
    RentRepository rentRepository;

    @Override
    public void save(Rent rent) {
        rentRepository.save(rent);
    }

    @Override
    public List<Rent> getAllRent() {
        return (List<Rent>) rentRepository.findAll();
    }

    @Override
    public Optional<Rent> findById(long id) {
        return rentRepository.findById(id);
    }

    @Override
    public void deleteRent(long id) {
        rentRepository.deleteById(id);
    }

    @Override
    public long findIdUser(long id) {
        return rentRepository.findIdUser(id);
    }

    @Override
    public List<Rent> findAllById(long id) {
        return (List<Rent>) rentRepository.findAllById(id);
    }

    @Override
    public Rent findByIdRent(long id) {
        return rentRepository.findByIdRent(id);
    }

    @Override
    public void updateComment(String comment, long id) {
        rentRepository.updateComment(comment,id);
    }

    @Override
    public void updateCheckComment(long id) {
        rentRepository.updateCheckComment(id);
    }

    @Override
    public List<Rent> getAllRentDESC() {
        return (List<Rent>) rentRepository.getAllRentDESC();
    }
}
