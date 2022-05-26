package main.service.impl;

import javax.transaction.Transactional;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import main.beans.UserCodeDto;
import main.config.security.JwtTokenProvider;
import main.entity.*;
import main.repository.UserCodeVerifyRepository;
import main.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import main.repository.UserRepository;

import java.text.DecimalFormat;
import java.util.*;

@Service
@Repository
@Transactional(rollbackOn = Exception.class)
@EnableTransactionManagement(proxyTargetClass = true)
public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserCodeVerifyRepository userCodeVerifyRepository;

    @Autowired
    JwtTokenProvider tokenProvider;

    public static final String ACCOUNT_SID = "AC2b4ddd238584a690c30b4bfc60d30b1b";
    public static final String AUTH_TOKEN = "0ca81b767536572e2bdd13f9c2e9b7b1";

    @Override
    public User getUserInfoByName(String username) {
        try {
            User result = userRepository.findByEmail(username);
            if (result == null || null == result.getEmail()) {
                throw new RuntimeException("username not exist!");
            } else {
                return result;
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<User> getListUser() {
        try {
            return userRepository.findAll();
        }
        catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public User findUserByPhone(String phone) {
        try {
            return userRepository.findByPhoneAndIsPending(phone, "0");
        }
        catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public User findById(Integer id) {
        try {
            return userRepository.findById(id).get();
        }
        catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public User findUserByUid(String uid) {

        try {
            return userRepository.findUserByUuId(uid);
        }
        catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void updateUser(User user) {
        try {
            userRepository.save(user);
        }
        catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public User registerUser(User user) {
        try {
            User user1 = userRepository.save(user);
            return user1;
        }
        catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public UserCodeVerify generateOtp(User user, String phone) {
        String otp= new DecimalFormat("000000").format(new Random().nextInt(999999));
        try {
            UserCodeVerify userCodeVerify = new UserCodeVerify();
            userCodeVerify.setUserCode(otp);
            userCodeVerify.setDateCode(new Date());
            userCodeVerify.setIdUser(user.getId());
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(
                            new com.twilio.type.PhoneNumber(phone),
                            new com.twilio.type.PhoneNumber("+19403945818"),
                            "your ZoJoy vertification code is: " + otp)
                    .create();
            return  userCodeVerifyRepository.save(userCodeVerify);
        }
        catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public User checkSmsUser(String sms, Integer uid) {
        try {
            UserCodeVerify userCodeVerify = userCodeVerifyRepository.findById(uid).get();
            if(userCodeVerify.getUserCode().equals(sms)){
                long dateOtp =  userCodeVerify.getDateCode().getTime();
                long dateOtp1 = new Date().getTime();
                long sumDate = dateOtp1 - dateOtp;
                if(dateOtp1 - dateOtp > 60*1000){
                    return null;
                }
                else{
                    // Trả về jwt cho người dùng.
                    User user = userRepository.findById(userCodeVerify.getIdUser()).get();
                    String jwt = tokenProvider.generateTokenUser(user.getId());
                    user.setAccessToken(jwt);
                    userRepository.save(user);
                    return user;
                }
            }
            return null;
        }
        catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }


}
