package main.entity;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_code")
public class UserCodeVerify {
    private Integer idUserCode;
    private String userCode;
    private Date dateCode;
    private Integer idUser;


    public UserCodeVerify() {
    }

    public UserCodeVerify(Integer idUserCode, String userCode, Date dateCode, Integer idUser) {
        this.idUserCode = idUserCode;
        this.userCode = userCode;
        this.dateCode = dateCode;
        this.idUser = idUser;
    }

    @Id
    @Column(name = "id_user_code")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getIdUserCode() {
        return idUserCode;
    }

    public void setIdUserCode(Integer idUserCode) {
        this.idUserCode = idUserCode;
    }

    @Basic
    @Column(name = "user_code")
    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    @Basic
    @Column(name = "date_code")
    public Date getDateCode() {
        return dateCode;
    }

    public void setDateCode(Date dateCode) {
        this.dateCode = dateCode;
    }

    @Basic
    @Column(name = "id_user")
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }
}
