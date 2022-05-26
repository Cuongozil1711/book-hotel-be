package main.controller;

import main.entity.Hotel;
import main.entity.Room;
import main.entity.UserRoom;
import main.service.HotelService;
import main.service.UserRoomService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RoomController {

    private static final Logger logger = LogManager.getLogger(RoomController.class);

    @Autowired
    HotelService hotelService;

    @Autowired
    UserRoomService userRoomService;


    @RequestMapping(value = "/getListRoom", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Room> getListRoom(@RequestParam("idHotel") String idHotel) {
        if(idHotel.isEmpty())
            return null;
        return hotelService.getListRoomByHotel(Integer.valueOf(idHotel));
    }

    @RequestMapping(value = "/bookRoom", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Integer bookRoom(@RequestBody UserRoom us) {
        userRoomService.insertUserRoom(us);
        return 200;
    }

    @RequestMapping(value = "/insertRoom", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public int insertRoom(@RequestBody Room room) {
        hotelService.insertRoom(room);
        return 200;
    }

    @RequestMapping(value = "/listUserRoom", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<UserRoom> listUserRoom(@RequestParam("id") Integer id) {
        return userRoomService.getListUserRoom(id);
    }
}
