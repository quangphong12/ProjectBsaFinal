package com.example.projectbsa.Controller;

import com.example.projectbsa.Entity.Account;
import com.example.projectbsa.Entity.User;
import com.example.projectbsa.Entity.Voucher;
import com.example.projectbsa.Security.AES256;
import com.example.projectbsa.Service.*;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/account")

public class AccountController {
    @Autowired
    UserService userService;
    @Autowired
    AccountService accountService;
    @Autowired
    BusService busService;
    @Autowired
    DriverService driverService;
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    RentService rentService;
    @Autowired
    EmailSenderService emailSenderService;
    @Autowired
    AES256 aes256;
    @Autowired
    VoucherService voucherService;

    /**
     * Trang chủ khi chưa login
     * @param session
     * @return
     */
    @RequestMapping("/")
    public String index(HttpSession session){
        session.removeAttribute("USERNAME");
        session.removeAttribute("ROLE");
        return "index";
    }

    /**
     * Trang giới thiệu về công ty khi chưa login
     * @param session
     * @return
     */
    @RequestMapping("/introduction")
    public String introduction(HttpSession session){
        session.removeAttribute("USERNAME");
        session.removeAttribute("ROLE");
        return "introduction";
    }

    /**
     * Trang tuyển dụng của công ty khi chưa login
     * @param session
     * @return
     */
    @RequestMapping("/recruitment")
    public String recruitment(HttpSession session){
        session.removeAttribute("USERNAME");
        session.removeAttribute("ROLE");
        return "recruitment";
    }

    /**
     * Trang login
     * @param session
     * @return
     */
    /// LOG IN
    @RequestMapping("/login")
    public String showLogIn(HttpSession session){
        session.removeAttribute("USERNAME");
        session.removeAttribute("ROLE");
        return "login";
    }

    /**
     * Check tài khoản login xem có tồn tại hay không và đã được kích hoạt chưa
     * Nếu có sẽ check đến quyền
     * @param modelMap
     * @param username
     * @param password
     * @param session
     * @return
     */
    @PostMapping("/checklogin")
    public String checkLogin(ModelMap modelMap, @RequestParam("username")String username,
                             @RequestParam("password")String password, HttpSession session){
        String encryptPassword=aes256.encrypt(password);
//        System.out.println(encryptPassword);
        if(accountService.checkLogin(username,encryptPassword)){
            if(accountService.checkRole(username)){
                session.setAttribute("USERNAME",username);
                session.setAttribute("ROLE","admin");
                return "admin/index_admin";
            }else{
                session.setAttribute("USERNAME",username);
                session.setAttribute("ROLE","user");
                return "user/index_user";
            }
        }else{
            modelMap.addAttribute("ERROR", "Username or password not exist");
        }
        return "login";
    }

    /**
     * Trang tạo tài khoản mới
     * @param model
     * @return
     */
    @RequestMapping(value="/register_account",method=RequestMethod.GET)
    public String registerAccount(Model model){
        Account account=new Account();
        model.addAttribute("account",account);
        return "register_account";
    }

    /**
     * Kiểm tra các điều kiện để lưu tài khoản mới và giửi mail kích hoạt
     * @param modelMap
     * @param emailClient
     * @param account
     * @param session
     * @param request
     * @return
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value="/save_new_account",method= RequestMethod.POST)
    public String addAccount(ModelMap modelMap,
                             @RequestParam("email")String emailClient,Account account,
                             HttpSession session,
                             HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        if(userService.countEmail(emailClient)>0){
            modelMap.addAttribute("ERROR", "Email đã tồn tại");
            return "register_account";
        }
        if(accountService.checkUP(account)){
            String SiteURL= request.getRequestURL().toString();
//            System.out.println(emailClient);
            session.setAttribute("Register_Email",emailClient);
            String randomCode = RandomString.make(64);
            session.setAttribute("CODE_Register",randomCode);
            String encryptPassword=aes256.encrypt(account.getPassword());
            account.setPassword(encryptPassword);
            account.setRole("user");
            account.setVerificationCode(randomCode);
            account.setEnabled(false);
            session.setAttribute("USERNAME",account.getUsername());
            accountService.sendVerificationEmail(account,emailClient,SiteURL.replace(request.getServletPath(), ""),randomCode);
            accountService.saveAccount(account);
            return "notification_account";
        }else{
            modelMap.addAttribute("ERROR", "Username đã tồn tại");
        }
        return "register_account";
    }

    /**
     * xác minh tài khoản nhờ email
     * @param session
     * @return
     */
    @GetMapping("/verify")
    public String verifyAccount(HttpSession session){
        String code=(String) session.getAttribute("CODE_Register");
        if(code!=null && accountService.verify(code)==true){
            session.removeAttribute("CODE_Register");
            return "verify_success";
        }
        return "verify_failed";
    }

    /**
     * Trang tạo mật khẩu mới
     * @param model
     * @return
     */
    @RequestMapping(value="/forgot_password")
    public String forgotPass(Model model){
        Account account=new Account();
        model.addAttribute("account",account);
        return "forgot_password";
    }

    /**
     * check điều kiện để lưu và gửi mail kích hoạt
     * @param modelMap
     * @param emailClient
     * @param account
     * @param session
     * @param request
     * @return
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value="/changePass",method=RequestMethod.POST)
    public String changePass(ModelMap modelMap,
                             @RequestParam("email")String emailClient,
                             Account account,
                             HttpSession session,
                             HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        if(!accountService.checkUP1(account,emailClient)){
            String SiteURL= request.getRequestURL().toString();
            String randomCode = RandomString.make(64);
            session.setAttribute("CODE_NewPass",randomCode);
            String encryptPassword=aes256.encrypt(account.getPassword());
            account.setVerificationCode(randomCode);
            account.setPassword(encryptPassword);
            accountService.sendChangePassword(account.getUsername(),emailClient,SiteURL.replace(request.getServletPath(), ""),randomCode);
            accountService.updateAccount(account);
            return "notification_account";
        }else{
            modelMap.addAttribute("ERROR", "Username hoặc Email không đúng");
        }
        return "forgot_password";
    }

    /**
     * Kích hoạt mật khẩu mới
     * @param session
     * @return
     */
    @GetMapping("/verifyNewPass")
    public String verifyNewPass(HttpSession session){
        String code=(String) session.getAttribute("CODE_NewPass");
        if(code!=null && accountService.verify(code)==true){
            session.removeAttribute("CODE_NewPass");
            return "verify_password";
        }
        return "verify_failed_change_password";
    }

    /**
     * Trang hiển thị để người dùng cập nhật thông tin cá nhân sau khi
     * kích hoạt tài khoản thành công
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/register_user", method =RequestMethod.GET)
    public String registerUser(Model model,HttpSession session){
        User user= new User();
        user.setEmail((String) session.getAttribute("Register_Email"));
        model.addAttribute("user",user);
        return "register_user";
    }

    /**
     * lưu thông tin cá nhân của người dùng mới tạo
     * tạo sẵn thông tin voucher ngay khi tạo thông tin người dùng mới
     * @param session
     * @param user
     * @param voucher
     * @return
     */
    @RequestMapping(value="/save_new_user",method = RequestMethod.POST)
    public String addUser(HttpSession session,
                          User user,
                          Voucher voucher){
//        System.out.println(session.getAttribute("USERNAME"));
        user.setUsername((String)session.getAttribute("USERNAME"));
        user.setPoint(0);
        user.setEmail((String) session.getAttribute("Register_Email"));
        session.removeAttribute("Register_Email");
        session.removeAttribute("USERNAME");
        session.removeAttribute("ROLE");
        userService.save(user);
        voucher.setId_user(user.getId());
        voucher.setVC10K(0);
        voucher.setVC20K(0);
        voucher.setVCG5P(0);
        voucherService.save(voucher);
        return "login";
    }

    /**
     * Logout tài khoản ra khỏi hệ thống
     * @param session
     * @return
     */
    ///LOG OUT
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("USERNAME");
        session.removeAttribute("ROLE");
        return "login";
    }

}
