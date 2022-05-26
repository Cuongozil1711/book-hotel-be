package main.service.impl;

import main.beans.HistoryBookRoom;
import main.entity.Hotel;
import main.entity.Room;
import main.entity.User;
import main.entity.UserRoom;
import main.repository.HotelRepository;
import main.repository.RoomRepository;
import main.repository.UserRoomRepository;
import main.service.UserRoomService;
import main.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Repository
@Transactional(rollbackOn = Exception.class)
@EnableTransactionManagement(proxyTargetClass = true)
public class UserRoomServiceImpl implements UserRoomService {

    private static final Logger logger = LogManager.getLogger(UserRoomServiceImpl.class);

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    RoomRepository roomRepository;
    @Autowired
    UserRoomRepository userRoomRepository;

    @Override
    public List<Room> findListRoomIsNoDelete(Integer idUser) {
        try {
            List<UserRoom> roomList = userRoomRepository.findByIdUserAndIsDelete(idUser, "0");
            List<Room> rooms = new ArrayList<>();
            for(UserRoom us: roomList){
                rooms.add(roomRepository.findById(us.getIdRoom()).get());
            }
            return rooms;
        }
        catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Room> findListRoomIsDelete(Integer idUser) {
        try {
            List<UserRoom> roomList = userRoomRepository.findByIdUserAndIsDelete(idUser, "1");
            List<Room> rooms = new ArrayList<>();
            for(UserRoom us: roomList){
                rooms.add(roomRepository.findById(us.getIdRoom()).get());
            }
            return rooms;

        }
        catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);

        }
    }

    @Override
    public List<HistoryBookRoom> getListBookRom(Integer idUser, String isDelete) {
        try {
            List<UserRoom> roomList = userRoomRepository.findByIdUserAndIsDelete(idUser, isDelete);
            List<HistoryBookRoom> list = new ArrayList<>();
            for(UserRoom us: roomList){
                HistoryBookRoom historyBookRoom = new HistoryBookRoom();
                Hotel hotel = hotelRepository.findById(roomRepository.findById(us.getIdRoom()).get().getIdHotel()).get();
                historyBookRoom.setRoom(roomRepository.findById(us.getIdRoom()).get());
                historyBookRoom.setHotel(hotel);
                historyBookRoom.setUserRoom(us);
                list.add(historyBookRoom);
            }
            return list;
        }
        catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void insertUserRoom(UserRoom us) {
        try {
            userRoomRepository.save(us);
        }
        catch(Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<UserRoom> getListUserRoom(Integer id) {
        try {
            return userRoomRepository.findAllById(Collections.singleton(id));
        }
        catch(Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

}
