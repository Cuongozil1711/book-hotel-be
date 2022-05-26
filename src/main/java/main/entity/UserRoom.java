package main.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_room")
public class UserRoom {
    private Integer idUserRoom;
    private Integer idUser;
    private Integer idRoom;
    private String dateFrom;
    private String dateTo;
    private String isPayMent;
    private String isDelete;

    public UserRoom(Integer idUserRoom, Integer idUser, Integer idRoom, String dateFrom, String dateTo, String isPayMent) {
        this.idUserRoom = idUserRoom;
        this.idUser = idUser;
        this.idRoom = idRoom;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.isPayMent = isPayMent;
    }

    public UserRoom() {

    }

    @Id
    @Column(name = "id_user_room")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getIdUserRoom() {
        return idUserRoom;
    }

    public void setIdUserRoom(Integer idUserRoom) {
        this.idUserRoom = idUserRoom;
    }

    @Basic
    @Column(name = "id_user")
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "id_room")
    public Integer getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Integer idRoom) {
        this.idRoom = idRoom;
    }

    @Basic
    @Column(name = "date_From")
    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    @Basic
    @Column(name = "date_To")
    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    @Basic
    @Column(name = "is_Payment")
    public String getIsPayMent() {
        return isPayMent;
    }

    public void setIsPayMent(String isPayMent) {
        this.isPayMent = isPayMent;
    }

    @Basic
    @Column(name = "is_Delete")
    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }
}
