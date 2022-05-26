package main.entity;


import javax.persistence.*;

@Entity
@Table(name = "hotel")
public class Hotel {
    private Integer id;
    private String name;
    private String address;
    private String describe;
    private String star;
    private String image;
    private String phone;

    public Hotel(Integer id, String name, String address, String describe, String star, String image, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.describe = describe;
        this.star = star;
        this.image = image;
        this.phone = phone;
    }

    public Hotel() {
    }

    @Id
    @Column(name = "id_hotel")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name_hotel")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "describe_hotel")
    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Basic
    @Column(name = "star")
    public String getStar() {
        return star;
    }

    public void setStar(String start) {
        this.star = start;
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
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
