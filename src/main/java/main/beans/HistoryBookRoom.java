package main.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.entity.Hotel;
import main.entity.Room;
import main.entity.UserRoom;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoryBookRoom {
    private Hotel hotel;
    private UserRoom userRoom;
    private Room room;
}
