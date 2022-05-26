package main.controller;

import main.beans.ReviewHotel;
import main.entity.Hotel;
import main.entity.User;
import main.entity.UserHotel;
import main.entity.UserHotelLike;
import main.service.HotelService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HotelController {

    private static final Logger logger = LogManager.getLogger(HotelController.class);

    public HotelController() {

    }

    @Autowired
    HotelService hotelService;


    @RequestMapping(value = "/getHotel", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Hotel> getHotel() {
        return hotelService.getListHotel();
    }


    @RequestMapping(value = "/getReview", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<ReviewHotel> getReview(@RequestParam("idHotel") Integer idHotel) {
        return hotelService.getReviewOfHotel(idHotel);
    }

    @RequestMapping(value = "/reviewHotel", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public int reviewHotel(@RequestBody UserHotel userHotel) {
        hotelService.insertHotel(userHotel);
        return 200;
    }

    @RequestMapping(value = "/insertHotel", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public int insertHotel(@RequestBody Hotel hotel) {
        hotelService.insertHotel(hotel);
        return 200;
    }

    @RequestMapping(value = "/updateUserLike", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public int updateUserLike(@RequestBody UserHotelLike userHotelLike) {
        hotelService.updateUserHotelLike(userHotelLike);
        return 200;
    }

    @RequestMapping(value = "/listUserLike", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<UserHotelLike> insertHotel(@RequestParam("idHotel") Integer idHotel) {
        return hotelService.getListAll(idHotel);
    }

    @RequestMapping(value = "/deleteBookRoom", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public int deleteBookRoom(@RequestParam("id") Integer id) {
        hotelService.deleteBookRoom(id);
        return 200;
    }

    @RequestMapping(value = "/findListHotelUserLike", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Hotel> findListHotelUserLike(@RequestParam("id") Integer id) {
        return hotelService.findListHotelUserLike(id);
    }

    @RequestMapping(value = "/getListHotelLike", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Hotel> getListHotelLike() {
        return hotelService.findListHotelManyLike();
    }
}
