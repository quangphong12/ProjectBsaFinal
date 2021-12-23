package com.example.projectbsa.Repository;

import com.example.projectbsa.Entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    @Query(value="select s.id from schedule s where s.id_schedule=?1",nativeQuery = true)
    long findIdUser(long id);

    @Query(value="update Schedule set [check]=?1",nativeQuery = true)
    void updateSchedule(String check);

    @Query(value ="SELECT * FROM Schedule s WHERE s.id=?1 ORDER BY s.id_schedule DESC",nativeQuery = true)
    List<Schedule> findAllById(long id);

    @Query(value="SELECT * FROM Schedule s WHERE s.id_schedule=?1",nativeQuery = true)
    Schedule findByIdSchedule(long id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value="update Schedule set comment=?1 where id_schedule=?2",nativeQuery = true)
    void updateComment(String comment, long id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value="update Schedule set enabled=1 where id_schedule=?1",nativeQuery = true)
    void updateCheckComment(long id);

    @Query(value="SELECT SUM(s.order_seats) FROM Schedule s WHERE s.place=?1  and s.[time]=?2 and s.[check]='Done' and s.date_seats=?3",nativeQuery = true)
    Integer sumOrderedSeats(String place,String time,String date);

    @Query(value="SELECT * FROM Schedule s ORDER BY s.id_schedule DESC",nativeQuery = true)
    List<Schedule> getAllScheduleDESC();
}
