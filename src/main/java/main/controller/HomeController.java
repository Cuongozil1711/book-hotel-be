package main.controller;

import main.beans.HistoryBookRoom;
import main.beans.UserCodeDto;
import main.beans.Userbean;
import main.common.CommonConst;
import main.common.URLConst;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import main.config.security.JwtTokenProvider;
import main.entity.User;
import main.entity.UserCodeVerify;
import main.service.AmazonS3Service;
import main.service.UserRoomService;
import main.service.UserService;
import org.apache.http.protocol.HTTP;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;


@RestController
public class HomeController extends BaseController{
    private static final Logger logger = LogManager.getLogger(HomeController.class);

    @Autowired
    AmazonS3Service amazonS3Service;

    @Autowired
    UserRoomService userRoomService;

    @Autowired
    UserService userService;

    @Autowired
    JwtTokenProvider tokenProvider;

    public static final String ACCOUNT_SID = "AC2b4ddd238584a690c30b4bfc60d30b1b";
    public static final String AUTH_TOKEN = "0ca81b767536572e2bdd13f9c2e9b7b1";

    public HomeController() {

    }
    @RequestMapping(value = "/" , method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ModelAndView homeView(HttpServletRequest request) {
        return new ModelAndView("redirect:/water");
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public UserCodeDto verifyUser(@RequestParam("phone") String phone) {
        if(userService.findUserByPhone(phone) != null){
            UserCodeVerify ucv = userService.generateOtp(userService.findUserByPhone(phone), phone);
            UserCodeDto userCodeDto = new UserCodeDto();
            userCodeDto.setUiId(ucv.getIdUserCode());
            userCodeDto.setUserCode("Nocode");
            return userCodeDto;
        }
        return null;
    }

    @RequestMapping(value = "/checkSmsUser", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public User verifyOtp(@RequestParam("otp") String otp, @RequestParam("uiId") Integer uiId) {
        return userService.checkSmsUser(otp, uiId);
    }

    @RequestMapping(value = "/getListUser", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getListUser() {
        return userService.getListUser();
    }

    @RequestMapping(value = "/loginFaceOrGoogle", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public User loginFaceOrGoogle(@RequestBody User user) {
        User usdt = userService.findUserByUid(user.getUuId());
        if(usdt != null){
            String jwt = tokenProvider.generateTokenUser(usdt.getId());
            usdt.setName(user.getName());
            //usdt.setImage(user.getImage());
            usdt.setEmail(user.getEmail());
            usdt.setPhone(user.getPhone());
            usdt.setIsPending("0");
            usdt.setTypeLogin(user.getTypeLogin());
            usdt.setAccessToken(jwt);
            userService.updateUser(usdt);
        }
        else{
            usdt = new User();
            usdt.setUuId(user.getUuId());
            usdt.setName(user.getName());
            usdt.setEmail(user.getEmail());
            usdt.setPhone(user.getPhone());
            usdt.setImage(user.getImage());
            usdt.setIsPending("0");
            usdt.setTypeLogin(user.getTypeLogin());
            User res = userService.registerUser(usdt);
            String jwt = tokenProvider.generateTokenUser(res.getId());
            res.setAccessToken(jwt);
            userService.updateUser(res);
            return res;
        }
        return usdt;
    }


    @RequestMapping(value = "/updateUser", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public User upDateUser(@RequestPart(name = "file") MultipartFile multipartFile,
                           @RequestParam("id") Integer id,
                           @RequestParam(value = "phone", required = false) String phone, @RequestParam("email") String email,
                           @RequestParam(value = "date", required = false) String date,
                           @RequestParam("gender") String gender) {
        User user = userService.findById(id);
        user.setPhone(phone);
        user.setEmail(email);
        user.setAge(date);
        user.setGener(gender);
        String ret = "";
        if(multipartFile != null){
            ret = amazonS3Service.uploadFileUrl(multipartFile);
        }
        user.setImage(ret);
        userService.updateUser(user);
        return user;
    }

    @RequestMapping(value = "/historyBook", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<HistoryBookRoom> getHistoryBook(@RequestParam("id") Integer id, @RequestParam("isDelete") String isDelete){
        return userRoomService.getListBookRom(id, isDelete);
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public UserCodeDto registerUser(@RequestParam("phone") String phone, @RequestParam("name") String name){
        UserCodeDto userCodeDto= new UserCodeDto();
        UserCodeVerify userCodeVerify = new UserCodeVerify();
        User user = new User();
        if(userService.findUserByPhone(phone) != null){
           return null;
        }
        else{
            user.setPhone(phone);
            user.setName(name);
            user.setUuId(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
            user.setIsPending("1");
            user =userService.registerUser(user);
            String otp= new DecimalFormat("000000").format(new Random().nextInt(999999));
            userCodeVerify = new UserCodeVerify();
            userCodeVerify.setUserCode(otp);
            userCodeVerify.setDateCode(new Date());
            userCodeVerify.setIdUser(user.getId());
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(
                            new com.twilio.type.PhoneNumber(phone),
                            new com.twilio.type.PhoneNumber("+19403945818"),
                            "your ZoJoy vertification code is: " + otp)
                    .create();
        }

        userCodeDto.setUiId(userCodeVerify.getIdUserCode());
        userCodeDto.setUserCode("Nocode");
        return userCodeDto;
    }


    @RequestMapping(value = "/checkToken", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public @ResponseBody Integer checkToken(){
        try {
            Authentication authen = SecurityContextHolder.getContext().getAuthentication();
            if (null != authen && !authen.getPrincipal().equals("anonymousUser")) {
                return 200;
            } else {
                return 400;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return 400;
    }


}

