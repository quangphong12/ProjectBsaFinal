package com.example.projectbsa.Schedule;

import com.example.projectbsa.Entity.Travel;
import com.example.projectbsa.Service.ScheduleService;
import com.example.projectbsa.Service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Configuration
@EnableScheduling
public class SpringConfig {

    @Autowired
    TravelService travelService;
    @Autowired
    ScheduleService scheduleService;

    /**
     * https://www.freeformatter.com/cron-expression-generator-quartz.html
     * 12h đêm
     */
    @Scheduled(cron="0 0 0 * * ?")
    public void scheduleReset(){
        travelService.resetEmptySeat();
        travelService.resetOrderedSeat();
    }

    /**
     * 1p update 1 lần
     */
    @Scheduled(cron="* * * ? * *")
    public void updateSeatsBus(){
        for(int i=100;i<=1000;i++){
            int idTravel=i;
            Optional<Travel> checkTravel= travelService.findTravelById(idTravel);
            if(!checkTravel.isPresent()){
                break;
            }
            Travel travel = travelService.findPlaceAndTimeById(idTravel);
            String place = travel.getPlace();
            String travel_time = travel.getTravelTime();
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateOrderSeats = dateFormat.format(date);
            int sumOrderSeats;
            if(scheduleService.sumOrderedSeats(place,travel_time,dateOrderSeats) == null){
                sumOrderSeats = 0;
            }
            else{
                sumOrderSeats = (int ) scheduleService.sumOrderedSeats(place,travel_time,dateOrderSeats);
            }
            travelService.updateOrderedSeat(sumOrderSeats,idTravel);
            travelService.updateEmptySeat(idTravel);
        }
    }
}
