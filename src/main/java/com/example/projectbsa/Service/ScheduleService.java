package com.example.projectbsa.Service;

import com.example.projectbsa.Entity.Schedule;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ScheduleService {
    void save(Schedule schedule);

    List<Schedule> getAllSchedule();

    Optional<Schedule> findById(long id);

    void deleteSchedule(long id);

    ///

    long findIdUser(long id);

    void updateSchedule(String check);

    List<Schedule> findAllById(long id);

    Schedule findByIdSchedule(long id);

    void updateComment(String comment, long id);

    void updateCheckComment(long id);

    Integer sumOrderedSeats(String place,String time,String date);

    List<Schedule> getAllScheduleDESC();
}
