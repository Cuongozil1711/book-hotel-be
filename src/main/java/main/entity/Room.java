package main.entity;

import javax.persistence.*;

@Entity
@Table(name = "room")
public class Room {
    private Integer idRoom;
    private String name;
    private String floor;
    private String describe;
    private String price;
    private String image;
    private Integer idHotel;

    public Room(Integer idRoom, String name, String floor, String describe, String price,String image, Integer idHotel) {
        this.idRoom = idRoom;
        this.name = name;
        this.floor = floor;
        this.describe = describe;
        this.price = price;
        this.image = image;
        this.idHotel = idHotel;
    }


    public Room() {

    }

    @Id
    @Column(name = "id_room")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Integer getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Integer idRoom) {
        this.idRoom = idRoom;
    }

    @Basic
    @Column(name = "name_room")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "floor")
    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    @Basic
    @Column(name = "describe_room")
    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Basic
    @Column(name = "price")
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Basic
    @Column(name = "image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
