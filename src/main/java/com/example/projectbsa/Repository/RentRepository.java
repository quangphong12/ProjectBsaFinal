package com.example.projectbsa.Repository;

import com.example.projectbsa.Entity.Rent;
import com.example.projectbsa.Entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<Rent,Long> {
    @Query(value="select t.id from rent t where t.id_rent=?1",nativeQuery = true)
    long findIdUser(long id);

    @Query(value ="SELECT * FROM Rent r WHERE r.id=?1 ORDER BY r.id_rent DESC",nativeQuery = true)
    List<Rent> findAllById(long id);

    @Query(value="SELECT * FROM Rent r WHERE r.id_rent=?1",nativeQuery = true)
    Rent findByIdRent(long id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value="update Rent set comment=?1 where id_rent=?2",nativeQuery = true)
    void updateComment(String comment, long id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value="update Rent set enabled=1 where id_rent=?1",nativeQuery = true)
    void updateCheckComment(long id);

    @Query(value ="SELECT * FROM Rent r ORDER BY r.id_rent DESC",nativeQuery = true)
    List<Rent> getAllRentDESC();
}
