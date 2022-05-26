package main.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_hotel_review")
public class UserHotel {
    private Integer idUserHotel;
    private String review;
    private String star;
    private String dateReview;
    private Integer idUser;
    private Integer idHotel;

    public UserHotel() {
    }

    public UserHotel(Integer idUserHotel, String review, String star, String dateReview, Integer idUser, Integer idHotel) {
        this.idUserHotel = idUserHotel;
        this.review = review;
        this.star = star;
        this.dateReview = dateReview;
        this.idUser = idUser;
        this.idHotel = idHotel;
    }


    @Id
    @Column(name = "id_user_hotel")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getIdUserHotel() {
        return idUserHotel;
    }

    public void setIdUserHotel(Integer idUserHotel) {
        this.idUserHotel = idUserHotel;
    }

    @Basic
    @Column(name = "review")
    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }


    @Basic
    @Column(name = "star")
    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    @Basic
    @Column(name = "date_review")
    public String getDateReview() {
        return dateReview;
    }

    public void setDateReview(String dateReview) {
        this.dateReview = dateReview;
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
