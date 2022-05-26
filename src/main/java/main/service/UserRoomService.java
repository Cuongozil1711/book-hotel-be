package main.service;

import main.beans.HistoryBookRoom;
import main.entity.Room;
import main.entity.User;
import main.entity.UserRoom;

import java.util.List;

public interface UserRoomService {
    public List<Room> findListRoomIsNoDelete(Integer idUser);
    public List<Room> findListRoomIsDelete(Integer idUser);

    public List<HistoryBookRoom> getListBookRom(Integer idUser, String isDelete);

    public void insertUserRoom(UserRoom us);
    public List<UserRoom> getListUserRoom(Integer id);
}
