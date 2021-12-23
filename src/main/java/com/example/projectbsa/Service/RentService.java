package com.example.projectbsa.Service;

import com.example.projectbsa.Entity.Rent;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RentService {
    void save(Rent rent);

    List<Rent> getAllRent();

    Optional<Rent> findById(long id);

    void deleteRent(long id);

    ///
    long findIdUser(long id);

    List<Rent> findAllById(long id);

    Rent findByIdRent(long id);

    void updateComment(String comment, long id);

    void updateCheckComment(long id);

    List<Rent> getAllRentDESC();
}
