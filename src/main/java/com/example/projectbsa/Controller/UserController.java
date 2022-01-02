package com.example.projectbsa.Controller;

import com.example.projectbsa.Entity.*;
import com.example.projectbsa.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AccountService accountService;
    @Autowired
    TravelService travelService;
    @Autowired
    BusService busService;
    @Autowired
    DriverService driverService;
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    RentService rentService;
    @Autowired
    VoucherService voucherService;

    /**
     * Trang chủ sau khi đăng nhập vào hệ thống
     * @param session
     * @return
     */
    @RequestMapping("/index_user")
    public String indexUser(HttpSession session){
        String s1=(String) session.getAttribute("ROLE");
        if( s1.equals("user")==false){
            return "login";
        }
        return "user/index_user";
    }

    /**
     * Trang hiển thị số lượng voucher theo người dùng
     * @param session
     * @return
     */
    @RequestMapping(value="/info_voucher")
    public ModelAndView infoVoucher(HttpSession session){
        String s=(String) session.getAttribute("USERNAME");
//        System.out.println(s);
        long id=userService.findId(s);
//        System.out.println(id);
        Voucher voucher= voucherService.findVoucherByIdUser(id);
        ModelAndView modelAndView= new ModelAndView("user/info_voucher");
        modelAndView.addObject("voucher",voucher);
        return modelAndView;
    }

    /**
     * Thông tin cá nhân của người dùng
     * @param session
     * @return
     */
    @RequestMapping(value="/profile")
    public ModelAndView profile(HttpSession session){
        String s=(String) session.getAttribute("USERNAME");
//        System.out.println(s);
        long id=userService.findId(s);
//        System.out.println(id);
        session.setAttribute("ID_USER",id);
        User user = userService.findUserById(id);
        session.setAttribute("POINT",user.getPoint());
        session.setAttribute("CMND",user.getCMND());
//        System.out.println(user.toString());
        ModelAndView mav= new ModelAndView("user/profile");
        mav.addObject("user",user);
        return mav;
    }

    /**
     * Chỉnh sửa thông tin cá nhân
     * @param session
     * @return
     */
    @RequestMapping(value="/edit_profile")
    public ModelAndView editProfile(HttpSession session){
        String username =(String) session.getAttribute("USERNAME");
//        System.out.println(username);
        long id=userService.findId(username);
//        System.out.println(id);
        session.setAttribute("ID_USER",id);
        User user = userService.findUserById(id);
        session.setAttribute("POINT",user.getPoint());
        session.setAttribute("CMND",user.getCMND());
//        System.out.println(user.toString());
        ModelAndView mav= new ModelAndView("user/edit_profile");
        mav.addObject("user",user);
        return mav;
    }

    /**
     * Lưu thông tin cá nhân mới chỉnh sửa
     * trừ 1 số trường không cho sửa đã được lưu lại an toàn
     * @param user
     * @param session
     * @param voucher
     * @return
     */
    @RequestMapping(value="/saveuser",method= RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user,HttpSession session, Voucher voucher){
        user.setId((long) session.getAttribute("ID_USER"));
        user.setPoint((int) session.getAttribute("POINT"));
        user.setCMND((long) session.getAttribute("CMND"));
        user.setUsername((String) session.getAttribute("USERNAME"));
        userService.save(user);
        return "user/profile";
    }

    /**
     * Trang lịch sử đặt vé và xe thuê
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/full_history")
    public String fullHistory(Model model,HttpSession session){
        String s1=(String) session.getAttribute("ROLE");
        if( s1.equals("user")==false){
            return "login";
        }
        String s=(String) session.getAttribute("USERNAME");
//        System.out.println(s);
        if(s != null) {
            long id=userService.findId(s);
//            System.out.println(id);
            List<Rent> rents=rentService.findAllById(id);
            model.addAttribute("rent",rents);
            List<Schedule> schedules=scheduleService.findAllById(id);
            model.addAttribute("schedule",schedules);
            return "user/full_history";
        }
        return "login";
    }

    /**
     * Đánh giá chuyến đi
     * @param scheduleId
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value="/comment_schedule",  method= RequestMethod.GET)
    public String commentSchedule(@RequestParam("id")Long scheduleId, Model model,HttpSession session){
        String s1=(String) session.getAttribute("ROLE");
        if( s1.equals("user")==false){
            return "login";
        }
        session.setAttribute("idScheduleComment",scheduleId);
        return "user/comment_schedule";
    }

    /**
     * Lưu đánh giá chuyến đi
     * @param comment
     * @param session
     * @return
     */
    @RequestMapping(value = "/save_comment_schedule", method = RequestMethod.POST)
    public String saveCommentSchedule(@RequestParam("comment") String comment, HttpSession session){
        long id = (long) session.getAttribute("idScheduleComment");
        Schedule schedule = scheduleService.findByIdSchedule(id);
//        System.out.println(schedule.toString());
        // check ngày
        String sDate1= schedule.getDateSeats();
        LocalDate date1 = LocalDate.parse(sDate1);
        LocalDate nowDate = LocalDate.now();
        int test1=nowDate.compareTo(date1);
        // check giờ
        long test2;
        if(test1 == 0) {
            String sTime1 = schedule.getTime();
            LocalTime nowTime = LocalTime.now();
            LocalTime time1 = LocalTime.parse(sTime1);
            Duration timeElapsed = Duration.between(time1, nowTime);
            test2 = (timeElapsed.toMillis() / 1000) / 3600;
        }else{
            test2 = 3;
        }
//        System.out.println(schedule.getCheck().equals("Done"));
        // kiểm tra điều kiện
//        System.out.println("test 1:"+ test1);
//        System.out.println("test 2:"+ test2);
        if (test1 >=0 && test2 >=3 && schedule.getCheck().equals("Done")){
            if(schedule.isEnabled() == false ){
                userService.updatePoint(200,schedule.getId());
                scheduleService.updateCheckComment(id);
            }
            scheduleService.updateComment(comment, id);
        }else{
            return "user/notification_comment_failed";
        }
        return "user/notification_comment_success";
    }

    /**
     * Đánh giá xe thuê
     * @param rentId
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value="/comment_rent",  method= RequestMethod.GET)
    public String commentRent(@RequestParam("id")Long rentId, Model model,HttpSession session){
        String s1=(String) session.getAttribute("ROLE");
        if( s1.equals("user")==false){
            return "login";
        }
        session.setAttribute("idRentComment",rentId);
        return "user/comment_rent";
    }

    /**
     * Lưu đánh giá xe thuê
     * @param comment
     * @param session
     * @return
     */
    @RequestMapping(value = "/save_comment_rent", method = RequestMethod.POST)
    public String saveCommentRent(@RequestParam("comment") String comment, HttpSession session){
        long id = (long) session.getAttribute("idRentComment");
        Rent rent = rentService.findByIdRent(id);
//        System.out.println(rent.toString());
        //check ngày giờ
        String dateTime = rent.getDateTimeEnd();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date dt = new Date();
        String dateTimeNow = dateFormat.format(dt);
        int test3 = dateTimeNow.compareTo(dateTime);
        if(test3 >=0 && rent.getCheck().equals("Done")){
            if(rent.isEnabled() == false ){
                userService.updatePoint(200,rent.getId());
                rentService.updateCheckComment(id);
            }
            rentService.updateComment(comment,rent.getId_rent());
        }else{
            return "user/notification_comment_failed";
        }
        return "user/notification_comment_success";
    }

    /**
     * trang đặt vé xe
     * @param model
     * @param session
     * @param modelMap
     * @return
     */
    @RequestMapping("/orderbus")
    public String orderBus(Model model,HttpSession session,ModelMap modelMap){
        String s1=(String) session.getAttribute("ROLE");
        if( s1.equals("user")==false){
            return "login";
        }
        checkTrip(model,"06:00","Hà Nội - Bắc Giang",session,modelMap);
        session.setAttribute("TRAVEL","06:00");
        session.setAttribute("PLACE","Hà Nội - Bắc Giang");
        return "user/orderbus";
    }

    /**
     * Tìm kiếm lộ trình theo yêu cầu
     * @param model
     * @param travel_time
     * @param place
     * @param session
     * @param modelMap
     * @return
     */
    ///Search trip
    @RequestMapping(value="/check_id_travel",method = RequestMethod.POST)
    public String checkTrip(Model model,
                            @RequestParam("travel")String travel_time,
                            @RequestParam("place")String place,
                            HttpSession session,
                            ModelMap modelMap){
        if(travel_time != null)  session.setAttribute("TRAVEL",travel_time);
        if(place != null) session.setAttribute("PLACE",place);
        ///idTime
        int idTravel=travelService.findIdTravel(travel_time,place);
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateOrderSeats = dateFormat.format(date);
//        System.out.println(dateOrderSeats);
        int sumOrderSeats;
        if(scheduleService.sumOrderedSeats(place,travel_time,dateOrderSeats) == null){
            sumOrderSeats = 0;
        }
        else{
            sumOrderSeats = (int ) scheduleService.sumOrderedSeats(place,travel_time,dateOrderSeats);
        }
//        System.out.println(sumOrderSeats);
//        System.out.println(idTravel);
        travelService.updateOrderedSeat(sumOrderSeats,idTravel);
        travelService.updateEmptySeat(idTravel);
        Travel travel1= travelService.findPlaceAndTimeById(idTravel);
        if(travel1.getSeatEmpty()==0){
            modelMap.addAttribute("ERROR", "Chuyến đi bạn vừa tìm đã hết chỗ");

        }
        //travel
        List<Travel> travel=travelService.findTravelbyId(idTravel);
//        System.out.println(travel.toString());
        model.addAttribute("travel",travel);
        //idBus
        long idBus=travelService.findIdBus(travel_time,place);
//        System.out.println(idBus);
        List<Bus> bus= busService.findBusbyId(idBus);
//        System.out.println(bus.toString());
        model.addAttribute("bus",bus);
        ///id Driver
        long idDriver=busService.findIdDriver(idBus);
//        System.out.println(idDriver);
        List<Driver> driver= driverService.findDriverbyId(idDriver);
//        System.out.println(driver.toString());
        model.addAttribute("driver",driver);
        return "user/orderbus";
    }

    /**
     * đặt vé xe
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value="/schedule",method=RequestMethod.GET)
    public String schedule(Model model,HttpSession session){
        String s1=(String) session.getAttribute("ROLE");
        if( s1.equals("user")==false){
            return "login";
        }
        Schedule schedule= new Schedule();
        schedule.setTime((String) session.getAttribute("TRAVEL"));
        schedule.setPlace((String) session.getAttribute("PLACE"));
        model.addAttribute("schedule",schedule);
        return "user/schedule";
    }

    /**
     * lưu vé xe đã đặt
     * @param modelMap
     * @param schedule
     * @param session
     * @return
     */
    /// order bus
    @RequestMapping(value="/saveschedule",method= RequestMethod.POST)
    public String saveSchedule(ModelMap modelMap,
                               Schedule schedule,
                               HttpSession session){
        ///id
        String s=(String) session.getAttribute("USERNAME");
//        System.out.println(s);
        long id=userService.findId(s);
//        System.out.println(id);
        schedule.setId(id);
        ///date
        String timeStamp = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss").format(Calendar.getInstance().getTime());
        schedule.setDate(timeStamp);
        ///checked
        schedule.setCheck("Checking");
        schedule.setComment(null);
        schedule.setEnabled(false);
        String voucher = schedule.getVoucher();
        int seats= schedule.getOrderSeats();
        // VC10K
        if(voucher.equals("VC10K")){
            int count= voucherService.countVC10K(id);
            if(count>0){
                int total = 80000*seats - 10000;
                schedule.setOrderSeats(seats);
                schedule.setVoucher(voucher);
                schedule.setTotal_money(total);
//                System.out.println(schedule.toString());
                scheduleService.save(schedule);
                voucherService.userVoucherVC10K(id);
            }else{
                modelMap.addAttribute("ERROR", "VC10K không còn để sử dụng");
                return "user/schedule";
            }
        }
        // VC20K
        else if(voucher.equals("VC20K")){
            int count= voucherService.countVC20K(id);
            if(count>0){
                int total = 80000*seats - 20000;
                schedule.setOrderSeats(seats);
                schedule.setVoucher(voucher);
                schedule.setTotal_money(total);
//                System.out.println(schedule.toString());
                scheduleService.save(schedule);
                voucherService.userVoucherVC20K(id);
            }else{
                modelMap.addAttribute("ERROR", "VC20K đã hết hãy mua thêm voucher để sử dụng");
                return "user/schedule";
            }
        }
        //VCG5P
        else if(voucher.equals("VCG5P")){
            int count= voucherService.countVCG5P(id);
            if(count>0){
                double discount = 80000*seats*0.05 ;
                if(discount > 50000){
                    discount = 50000;
                }
                int total = 80000*seats -((int) discount) ;
                schedule.setOrderSeats(seats);
                schedule.setVoucher(voucher);
                schedule.setTotal_money(total);
//                System.out.println(schedule.toString());
                scheduleService.save(schedule);
                voucherService.userVoucherVCG5P(id);
            }else{
                modelMap.addAttribute("ERROR", "VCG5P đã hết hãy mua thêm voucher để sử dụng");
                return "user/schedule";
            }
        }
        else{
            int total = 80000*seats;
            schedule.setOrderSeats(seats);
            schedule.setVoucher(voucher);
            schedule.setTotal_money(total);
//            System.out.println(schedule.toString());
            scheduleService.save(schedule);
        }
        return "redirect:/user/orderbus";
    }

    /**
     * Trang thuê xe
     * @param session
     * @return
     */
    @RequestMapping("/rentcar")
    public String rentCar(HttpSession session){
        String s1=(String) session.getAttribute("ROLE");
        if( s1.equals("user")==false){
            return "login";
        }
        return "user/rentcar";
    }

    /**
     * Trang đặt xe thuê theo nhu cầu
     * @param model
     * @param session
     * @return
     */
    ///rent
    @RequestMapping(value="/rent",method=RequestMethod.GET)
    public String rent(Model model,HttpSession session){
        String s1=(String) session.getAttribute("ROLE");
        if( s1.equals("user")==false){
            return "login";
        }
        Rent rent= new Rent();
        model.addAttribute("rent",rent);
        return "user/rent";
    }

    /**
     * Lưu thông tin thuê xe
     * @param rent
     * @param session
     * @return
     */
    /// save rent car
    @RequestMapping(value="/saverentcar",method= RequestMethod.POST)
    public String saveRent( Rent rent,
                            HttpSession session){
        ///id
        String s=(String) session.getAttribute("USERNAME");
//        System.out.println(s);
        long id=userService.findId(s);
//        System.out.println(id);
        rent.setId(id);
        ///set check
        rent.setCheck("Checking");
        rent.setComment(null);
        rent.setEnabled(false);
//        System.out.println(rent.toString());
        rentService.save(rent);
        return "/user/rentcar";
    }

    /**
     * Trang giới thiệu cho các tk đã login
     * @param session
     * @return
     */
    @RequestMapping("/introduction_user")
    public String introduction(HttpSession session){
        String s1=(String) session.getAttribute("ROLE");
        if( s1.equals("user")==false){
            return "login";
        }
        return"user/introduction_user";
    }

    /**
     * Trang  tuyển dụng cho tk đã login
     * @param session
     * @return
     */
    @RequestMapping("/recruitment_user")
    public String recruitment(HttpSession session){
        String s1=(String) session.getAttribute("ROLE");
        if( s1.equals("user")==false){
            return "login";
        }
        return"user/recruitment_user";
    }

    /**
     * Trang voucher để quy đổi
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/Voucher")
    public String voucher(Model model,HttpSession session){
        String s1=(String) session.getAttribute("ROLE");
        if( s1.equals("user")==false){
            return "login";
        }
        Voucher voucher= new Voucher();
        model.addAttribute("voucher",voucher);
        return "user/Voucher";
    }

    /**
     * Lưu voucher đã đổi của khách hàng
     * @param modelMap
     * @param voucher
     * @param session
     * @return
     */
    @RequestMapping(value="/saveVoucher",method= RequestMethod.POST)
    public String saveVoucher(ModelMap modelMap,Voucher voucher, HttpSession session){
        ///id
        String s=(String) session.getAttribute("USERNAME");
//        System.out.println(s);
        long id=userService.findId(s);
//        System.out.println(id);
        int point = (int) userService.checkPoint(id);
//        System.out.println(point);
        int VC10K = voucher.getVC10K();
        int VC20K = voucher.getVC20K();
        int VCG5P = voucher.getVCG5P();
        int totalVC= (VC10K*8000)+(VC20K*16000)+(VCG5P*30000);
//        System.out.println(totalVC);
        int point1=point-totalVC;
//        System.out.println(point1);
        if(point1<0){
            modelMap.addAttribute("ERROR1", "Bạn không đủ sao để đổi voucher");
            return "/user/Voucher";
        }else{
            userService.usePoint(point1,id);
            voucherService.addVoucherVC10K(VC10K,id);
            voucherService.addVoucherVC20K(VC20K,id);
            voucherService.addVoucherVCG5P(VCG5P,id);
            modelMap.addAttribute("ERROR2", "Đổi voucher thành công");
        }
        return "/user/Voucher";
    }

}
