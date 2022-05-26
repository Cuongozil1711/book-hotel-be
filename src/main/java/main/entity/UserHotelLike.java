package main.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_hotel_like")
public class UserHotelLike {
    private Integer id;
    private String userLike;
    private Integer idUser;
    private Integer idHotel;

    public UserHotelLike() {
    }

    public UserHotelLike(Integer id, String userLike, Integer idUser, Integer idHotel) {
        this.id = id;
        this.userLike = userLike;
        this.idUser = idUser;
        this.idHotel = idHotel;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_like")
    public String getUserLike() {
        return userLike;
    }

    public void setUserLike(String userLike) {
        this.userLike = userLike;
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
    @Column(name = "id_hotel")
    public Integer getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(Integer idHotel) {
        this.idHotel = idHotel;
    }
}
