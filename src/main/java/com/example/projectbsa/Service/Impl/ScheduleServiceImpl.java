package com.example.projectbsa.Service.Impl;

import com.example.projectbsa.Entity.Schedule;
import com.example.projectbsa.Repository.ScheduleRepository;
import com.example.projectbsa.Service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;
    @Override
    public void save(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    @Override
    public List<Schedule> getAllSchedule() {
        return (List<Schedule>) scheduleRepository.findAll();
    }

    @Override
    public Optional<Schedule> findById(long id) {
        return scheduleRepository.findById(id);
    }

    @Override
    public void deleteSchedule(long id) {
        scheduleRepository.deleteById(id);
    }

    @Override
    public long findIdUser(long id) {
        return scheduleRepository.findIdUser(id);
    }

    @Override
    public  void updateSchedule(String check){
        scheduleRepository.updateSchedule(check);
    }

    @Override
    public List<Schedule> findAllById(long id) {
        return (List<Schedule>) scheduleRepository.findAllById(id);
    }

    @Override
    public Schedule findByIdSchedule(long id) {
        return scheduleRepository.findByIdSchedule(id);
    }

    @Override
    public void updateComment(String comment, long id) {
         scheduleRepository.updateComment(comment,id);
    }

    @Override
    public void updateCheckComment(long id) {
        scheduleRepository.updateCheckComment(id);
    }

    @Override
    public Integer sumOrderedSeats(String place, String time, String date) {
        return scheduleRepository.sumOrderedSeats(place,time,date);
    }

    @Override
    public List<Schedule> getAllScheduleDESC() {
        return (List<Schedule>) scheduleRepository.getAllScheduleDESC();
    }
}
