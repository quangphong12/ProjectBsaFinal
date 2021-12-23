package com.example.projectbsa.Controller;

import com.example.projectbsa.Entity.*;
import com.example.projectbsa.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
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
     * Trang chủ admin
     * @param session
     * @return
     */
    @RequestMapping("/index_admin")
    public String indexAdmin(HttpSession session){
        String s=(String) session.getAttribute("ROLE");
        if( s.equals("admin")==false){
            return "login";
        }
        return "admin/index_admin";
    }

    ///================== List User ==================///

    /**
     * Trang hiển thị toàn bộ người dùng
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/full_user")
    public String fullUser(Model model,HttpSession session){
        String s=(String) session.getAttribute("ROLE");
        if( s.equals("admin")==false){
            return "login";
        }
        List<User> users=userService.getAllUserDESC();
        model.addAttribute("users",users);
        return "admin/full_user";
    }

    /**
     * Tìm kiếm theo username
     * @param model
     * @param username
     * @return
     */
    @RequestMapping("/check_user")
    public String checkUser(Model model,@RequestParam("username")String username ){
        List<User> users=userService.findUserByUserName(username);
        model.addAttribute("users",users);
        return "admin/full_user";
    }

    /**
     * Chỉnh sửa thông tin người dùng
     * @param userId
     * @param model
     * @param session
     * @return
     */
    /// Edit User
    @RequestMapping(value="/edit_user",  method= RequestMethod.GET)
    public String editUser(@RequestParam("id")Long userId, Model model,HttpSession session){
        String s=(String) session.getAttribute("ROLE");
        if( s.equals("admin")==false){
            return "login";
        }
        Optional<User> userEdit= userService.findById(userId);
        userEdit.ifPresent(user-> model.addAttribute("user",user));
        return "admin/edit_user";
    }

    /**
     * Lưu thông tin người dùng
     * @param user
     * @return
     */
    ///Save User
    @RequestMapping(value = "save_user", method = RequestMethod.POST)
    public String saveUser(User user) {
        userService.save(user);
        return "redirect:/admin/full_user";
    }

    /**
     * Xóa người dùng
     * @param userId
     * @return
     */
    ///Delete User
    @RequestMapping(value="/delete_user",method=RequestMethod.GET)
    public String deleteUser(@RequestParam("id")Long userId){
        userService.deleteUser(userId);
        return "redirect:/admin/full_user";
    }
    ///================== Voucher ==================///

    /**
     * Hiển thị toàn bộ voucher của từng người dùng
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/full_voucher")
    public String fullVoucher(Model model,HttpSession session){
        String s=(String) session.getAttribute("ROLE");
        if( s.equals("admin")==false){
            return "login";
        }
        List<Voucher> voucher= voucherService.getAllVoucher();
//        System.out.print(voucher.size());
        model.addAttribute("voucher",voucher);
        return "admin/full_voucher";
    }

    /**
     * tìm kiếm voucher theo username
     * @param model
     * @param username
     * @return
     */
    @RequestMapping("/check_voucher")
    public String checkVoucher(Model model,@RequestParam("username")String username ){
        long id = userService.findId(username);
        Voucher voucher= voucherService.findVoucherByIdUser(id);
        model.addAttribute("voucher",voucher);
        return "admin/full_voucher";
    }

    /**
     * Chỉnh sửa voucher
     * @param voucherId
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value="/edit_voucher", method = RequestMethod.GET)
    public String editVoucher(@RequestParam("id")Long voucherId, Model model,HttpSession session){
        String s=(String) session.getAttribute("ROLE");
        if( s.equals("admin")==false){
            return "login";
        }
        Optional<Voucher> voucherEdit= voucherService.findById(voucherId);
        voucherEdit.ifPresent(voucher -> model.addAttribute("voucher",voucher));
        long idUser=voucherService.findIdUser(voucherId);
        Optional<User> user =userService.findById(idUser);
        model.addAttribute("user",user);
        return "admin/edit_voucher";
    }

    /**
     * Lưu voucher
     * @param voucher
     * @return
     */
    @RequestMapping(value = "save_voucher", method = RequestMethod.POST)
    public String saveVoucher(Voucher voucher) {
        voucherService.save(voucher);
        return "redirect:/admin/full_voucher";
    }

    /**
     * Xóa voucher
     * @param userId
     * @return
     */
    @RequestMapping(value="/delete_voucher",method=RequestMethod.GET)
    public String deleteVoucher(@RequestParam("id")Long userId){
        voucherService.deleteVoucher(userId);
        return "redirect:/admin/full_voucher";
    }
    ///================== List Schedule ==================///

    /**
     * Hiển thị toàn bộ đặt vé xe
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/full_schedule")
    public String fullSchedule(Model model,HttpSession session){
        String s=(String) session.getAttribute("ROLE");
        if( s.equals("admin")==false){
            return "login";
        }
        List<Schedule> schedules= scheduleService.getAllScheduleDESC();
        model.addAttribute("schedule",schedules);
        return "admin/full_schedule";
    }

    /**
     * Tìm vé theo username
     * @param model
     * @param username
     * @return
     */
    @RequestMapping("/check_schedule")
    public String checkSchedule(Model model,@RequestParam("username")String username ){
        long id = userService.findId(username);
        List<Schedule> schedules= scheduleService.findAllById(id);
        model.addAttribute("schedule",schedules);
        return "admin/full_schedule";
    }

    /**
     * chỉnh sửa thông tin đặt vé
     * @param scheduleId
     * @param model
     * @param session
     * @return
     */
    ///Edit Schedule
    @RequestMapping(value="/edit_schedule",  method= RequestMethod.GET)
    public String editSchedule(@RequestParam("id")Long scheduleId, Model model,HttpSession session){
        String s=(String) session.getAttribute("ROLE");
        if( s.equals("admin")==false){
            return "login";
        }
        Optional<Schedule> scheduleEdit= scheduleService.findById(scheduleId);
        scheduleEdit.ifPresent(schedule -> model.addAttribute("schedule",schedule));
        long idUser=scheduleService.findIdUser(scheduleId);
        Optional<User> user =userService.findById(idUser);
        model.addAttribute("user",user);
//        System.out.println(scheduleEdit.toString());
//        System.out.println(user.toString());
        return "admin/edit_schedule";
    }

    /**
     * Lưu thông tin vé sau chỉnh sửa
     * check điều kiện để tích lũy sao
     * @param schedule
     * @return
     */
    ///Save Schedule
    @RequestMapping(value = "/save_schedule", method = RequestMethod.POST)
    public String saveSchedule(Schedule schedule) {
        System.out.println(schedule.toString());
        scheduleService.save(schedule);
        if(schedule.getCheck().equals("Done")){
            int point=schedule.getOrderSeats()*100;
            userService.updatePoint(point,schedule.getId());
        }
        if(schedule.getCheck().equals("Cancel")){
            long id = schedule.getId();
            System.out.println(schedule.getId());
            System.out.println(schedule.getVoucher());
            if(schedule.getVoucher().equals("VC10K")){
                voucherService.addVoucherVC10K(1,id);
            }else if(schedule.getVoucher().equals("VC20K")){
                voucherService.addVoucherVC20K(1,id);
            }else if(schedule.getVoucher().equals("VCG5P")){
                voucherService.addVoucherVCG5P(1,id);
            }
    }
        return "redirect:/admin/full_schedule";
    }

    /**
     * Xóa vé đã đặt
     * @param scheduleId
     * @return
     */
    ///Delete Schedule
    @RequestMapping(value="/delete_schedule",method=RequestMethod.GET)
    public String deleteSchedule(@RequestParam("id")long scheduleId){
        scheduleService.deleteSchedule(scheduleId);
        return "redirect:/admin/full_schedule";
    }
    ///================== List Rent ==================///

    /**
     * Toàn bộ người dùng thuê xe
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/full_rent")
    public String fullRent(Model model,HttpSession session){
        String s=(String) session.getAttribute("ROLE");
        if( s.equals("admin")==false){
            return "login";
        }
        List<Rent> rents= rentService.getAllRentDESC();
        model.addAttribute("rent",rents);
        return "admin/full_rent";
    }

    /**
     * Tìm theo username
     * @param model
     * @param username
     * @return
     */
    @RequestMapping("/check_rent")
    public String checkRent(Model model,@RequestParam("username")String username ){
        long id = userService.findId(username);
        List<Rent> rents= rentService.findAllById(id);
        model.addAttribute("rent",rents);
        return "admin/full_rent";
    }

    /**
     * Chỉnh sửa thông tin thuê xe
     * check điều kiện để tích lũy sao
     * @param rentId
     * @param model
     * @param session
     * @return
     */
    ///Edit Rent
    @RequestMapping(value="/edit_rent",  method= RequestMethod.GET)
    public String editRent(@RequestParam("id")long rentId,Model model,HttpSession session){
        String s=(String) session.getAttribute("ROLE");
        if( s.equals("admin")==false){
            return "login";
        }
        Optional<Rent> rentEdit= rentService.findById(rentId);
        rentEdit.ifPresent(rent -> model.addAttribute("rent",rent));
        long idUser=rentService.findIdUser(rentId);
        Optional<User> user =userService.findById(idUser);
        model.addAttribute("user",user);
//        System.out.println(rentEdit.toString());
//        System.out.println(user.toString());
        return "admin/edit_rent";
    }
    ///Save Rent
    @RequestMapping(value = "/save_rent", method = RequestMethod.POST)
    public String saveRent(Rent rent) {
        System.out.println(rent.toString());
        rentService.save(rent);
        if(rent.getCheck().equals("Done")){
            int point=rent.getType()*2000;
//            System.out.println(rent.getType());
            userService.updatePoint(point,rent.getId());
        }
        return "redirect:/admin/full_rent";
    }

    /**
     * xóa thông tin đặt xe
     * @param rentId
     * @return
     */
    ///Delete Rent
    @RequestMapping(value="/delete_rent",method = RequestMethod.GET)
    public String deleteRent(@RequestParam("id")long rentId){
        rentService.deleteRent(rentId);
        return "redirect:/admin/full_rent";
    }
    ///================== List Driver ==================///

    /**
     * Hiển thị toàn bộ thông tin lái xe
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/full_driver")
    public String fullDriver(Model model,HttpSession session){
        String s=(String) session.getAttribute("ROLE");
        if( s.equals("admin")==false){
            return "login";
        }
        List<Driver> drivers=driverService.findAll();
        model.addAttribute("drivers",drivers);
        return "admin/full_driver";
    }

    /**
     * Tìm tài xế theo tên
     * @param model
     * @param name
     * @return
     */
    @RequestMapping("/check_driver")
    public String checkDriver(Model model,@RequestParam("name")String name ){
        List<Driver> drivers=driverService.findDriverbyName(name);
        model.addAttribute("drivers",drivers);
        return "admin/full_driver";
    }

    /**
     * Thêm tài xế
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/add_driver")
    public String addDriver(Model model,HttpSession session){
        String s=(String) session.getAttribute("ROLE");
        if( s.equals("admin")==false){
            return "login";
        }
        model.addAttribute("driver", new Driver());
        return "admin/add_driver";
    }

    /**
     * chỉnh sửa tài xế
     * @param idDriver
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value="/edit_driver",method = RequestMethod.GET)
    public String editDriver(@RequestParam("id") long idDriver, Model model,HttpSession session){
        String s=(String) session.getAttribute("ROLE");
        if( s.equals("admin")==false){
            return "login";
        }
        Optional<Driver> driverEdit= driverService.findDriverById(idDriver);
        driverEdit.ifPresent(driver -> model.addAttribute("driver",driver));
        return "admin/edit_driver";
    }

    /**
     * lưu thông tin tài xế chỉnh sửa
     * @param driver
     * @return
     */
    @RequestMapping(value="/save_driver",method = RequestMethod.POST)
    public String saveDriver(Driver driver){
        driverService.saveDriver(driver);
        return "redirect:/admin/full_driver";
    }

    /**
     * xóa tài xế
     * @param idDriver
     * @return
     */
    @RequestMapping(value="/delete_driver",method = RequestMethod.GET)
    public String deleteDriver(@RequestParam("id") long idDriver){
        driverService.deleteDriver(idDriver);
        return "redirect:/admin/full_driver";
    }
    ///================== List Bus ==================///

    /**
     * Hiển thị toàn bộ xe khách
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/full_bus")
    public String fullBus(Model model,HttpSession session){
        String s=(String) session.getAttribute("ROLE");
        if( s.equals("admin")==false){
            return "login";
        }
        List<Bus> bus=busService.findAll();
        model.addAttribute("bus",bus);
        return "admin/full_bus";
    }

    /**
     * tìm kiếm xe khách theo biển số
     * @param model
     * @param licensePlateBus
     * @return
     */
    @RequestMapping("/check_bus")
    public String checkBus(Model model,@RequestParam("licensePlateBus")String licensePlateBus ){
        long id = busService.findIdBus(licensePlateBus);
        List<Bus> bus = busService.findBusbyId(id);
        model.addAttribute("bus",bus);
        return "admin/full_bus";
    }

    /**
     * thêm xe khách
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/add_bus")
    public String addBus(Model model,HttpSession session){
        String s=(String) session.getAttribute("ROLE");
        if( s.equals("admin")==false){
            return "login";
        }
        model.addAttribute("bus",new Bus());
        return "admin/add_bus";
    }

    /**
     * chỉnh thông tin xe khách
     * @param idBus
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value="/edit_bus",method=RequestMethod.GET)
    public String editBus(@RequestParam("id") long idBus, Model model,HttpSession session){
        String s=(String) session.getAttribute("ROLE");
        if( s.equals("admin")==false){
            return "login";
        }
        Optional<Bus> editBus= busService.findBusById(idBus);
        editBus.ifPresent(bus->model.addAttribute("bus",bus));
        return "admin/edit_bus";
    }

    /**
     * lưu xe mới
     * @param bus
     * @return
     */
    @RequestMapping(value="/save_bus",method = RequestMethod.POST)
    public String saveBus(Bus bus){
        busService.saveBus(bus);
        return "redirect:/admin/full_bus";
    }

    /**
     * xóa xe
     * @param idBus
     * @return
     */
    @RequestMapping(value="/delete_bus",method= RequestMethod.GET)
    public String deleteBus(@RequestParam("id") long idBus){
        busService.deleteBus(idBus);
        return "redirect:/admin/full_bus";
    }
    ///================== List Travel ==================///

    /**
     * Hiển thị toàn bộ lịch trình
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/full_travel")
    public String fullTravel(Model model,HttpSession session){
        String s=(String) session.getAttribute("ROLE");
        if( s.equals("admin")==false){
            return "login";
        }
        List<Travel> travel=travelService.findAll();
        model.addAttribute("travel",travel);
        return"admin/full_travel";
    }

    /**
     * tìm lộ trình theo biển số
     * @param model
     * @param licensePlateBus
     * @return
     */
    @RequestMapping("/check_travel")
    public String checkTravel(Model model,@RequestParam("licensePlateBus")String licensePlateBus ){
        long id = busService.findIdBus(licensePlateBus);
        List<Travel> travel=travelService.findTravelbyIdBus(id);
        model.addAttribute("travel",travel);
        return"admin/full_travel";
    }

    /**
     * thêm lộ trình
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/add_travel")
    public String addTravel(Model model,HttpSession session){
        String s=(String) session.getAttribute("ROLE");
        if( s.equals("admin")==false){
            return "login";
        }
        model.addAttribute("travel",new Travel());
        return "admin/add_travel";
    }

    /**
     * sửa lộ trình
     * @param idTravel
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value="/edit_travel",method = RequestMethod.GET)
    public String editTravel(@RequestParam("id") int idTravel,Model model,HttpSession session){
        String s=(String) session.getAttribute("ROLE");
        if( s.equals("admin")==false){
            return "login";
        }
        Optional<Travel> editTravel= travelService.findTravelById(idTravel);
        editTravel.ifPresent(travel -> model.addAttribute("travel",travel));
        return "admin/edit_travel";
    }

    /**
     * lưu lộ trình
     * @param travel
     * @return
     */
    @RequestMapping(value="/save_travel",method = RequestMethod.POST)
    public String saveTravel(Travel travel){
        travelService.saveTravel(travel);
        return "redirect:/admin/full_travel";
    }

    /**
     * xóa lộ trình
     * @param idTravel
     * @return
     */
    @RequestMapping(value="/delete_travel",method = RequestMethod.GET)
    public String deleteTravel(@RequestParam("id") int idTravel){
        travelService.deleteTravel(idTravel);
        return "redirect:/admin/full_travel";
    }

}
