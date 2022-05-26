package main.service;

import main.beans.ReviewHotel;
import main.entity.Hotel;
import main.entity.Room;
import main.entity.UserHotel;
import main.entity.UserHotelLike;

import java.util.List;

public interface HotelService {
    public List<Hotel> getListHotel();

    public List<Room> getListRoomByHotel(Integer idHotel);

    public List<ReviewHotel> getReviewOfHotel(Integer idHotel);
    public void insertHotel(UserHotel us);

    public void insertHotel(Hotel hotel);

    public void insertRoom(Room room);

    public List<UserHotelLike> getListAll(Integer idHotel);

    public void updateUserHotelLike(UserHotelLike like);

    public void deleteBookRoom(Integer id);

    public List<Hotel> findListHotelUserLike(Integer idUser);

    public List<Hotel> findListHotelManyLike();
}
