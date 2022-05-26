package main.service.impl;


import io.swagger.models.auth.In;
import main.beans.ReviewHotel;
import main.entity.*;
import main.repository.*;
import main.service.HotelService;
import main.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Repository
@Transactional(rollbackOn = Exception.class)
@EnableTransactionManagement(proxyTargetClass = true)
public class HotelServiceImpl implements HotelService {

    private static final Logger logger = LogManager.getLogger(HotelServiceImpl.class);

    @Autowired
    UserLikeRepository userLikeRepository;

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    UserHotelRepository userHotelRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRoomRepository userRoomRepository;

    @Override
    public List<Hotel> getListHotel() {
        try {
             List<Hotel> mList = hotelRepository.findAll();
            return mList;
        }
        catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Room> getListRoomByHotel(Integer idHotel) {
        try {
            List<Room> mList = roomRepository.findAllByIdHotel(idHotel);
            return mList;
        }
        catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<ReviewHotel> getReviewOfHotel(Integer idHotel) {
        try {
            List<UserHotel> userHotelList = userHotelRepository.findUserHotelByIdHotel(idHotel);
            List<ReviewHotel> result = new ArrayList<>();
            for(UserHotel item : userHotelList){
                User user = userService.findById(item.getIdUser());
                ReviewHotel rs = new ReviewHotel();
                rs.setNameReview(user.getName());
                rs.setReviewStar(item.getStar());
                rs.setDayReview(item.getDateReview());
                rs.setIdReview(item.getReview());
                result.add(rs);
            }
            return result;
        }
        catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void insertHotel(UserHotel us) {
        try {
            userHotelRepository.save(us);
        }
        catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void insertHotel(Hotel hotel) {
        try {
            hotelRepository.save(hotel);
        }
        catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void insertRoom(Room room) {
        try {
            roomRepository.save(room);
        }
        catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<UserHotelLike> getListAll(Integer idHotel) {
        try {
            return userLikeRepository.findAllByIdHotel(idHotel);
        }
        catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void updateUserHotelLike(UserHotelLike like) {
        try {
            userLikeRepository.save(like);
        }
        catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void deleteBookRoom(Integer id) {
        try {
            userRoomRepository.deleteById(id);
        }
        catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Hotel> findListHotelUserLike(Integer idUser) {
        try {
            List<UserHotelLike> userHotelLikes = userLikeRepository.findAllByIdUser(idUser);
            List<Hotel> hotelList = new ArrayList<>();
            for(UserHotelLike hotelLike : userHotelLikes){
                if(hotelLike.getIdHotel() != null && hotelLike.getUserLike().equals("1"))
                hotelList.add(hotelRepository.findById(hotelLike.getIdHotel()).get());
            }
            return hotelList;
        }
        catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Hotel> findListHotelManyLike() {
        try {
            List<UserHotelLike> userHotelLikes = userLikeRepository.findAll();
            List<Hotel> hotelList = new ArrayList<>();
            HashMap<Integer, Integer> hashMap = new HashMap<>();
            for(UserHotelLike hotelLike : userHotelLikes) {
                if (hashMap.containsKey(hotelLike.getIdHotel())) {
                    hotelList.add(hotelRepository.findById(hotelLike.getIdHotel()).get());
                } else if (hotelLike.getIdHotel() != null && hotelLike.getUserLike().equals("1"))
                    hashMap.put(hotelLike.getIdHotel(), 0);
                //hotelList.add(hotelRepository.findById(hotelLike.getIdHotel()).get());
            }
            return hotelList;
        }
        catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }


}
