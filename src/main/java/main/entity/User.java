package main.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    private Integer id;
    private String uuId;
    private String email;
    private String name;
    private String password;
    private String phone;
    private String age;
    private String address;
    private String typeLogin;
    private String gener;
    private String image;
    private String isPending;
    private String accessToken;

    public User() {
    }


    public User(Integer id, String uuId, String email, String name, String password, String phone, String age, String address, String typeLogin, String gener, String image, String isPending, String accessToken) {
        this.id = id;
        this.uuId = uuId;
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.age = age;
        this.address = address;
        this.typeLogin = typeLogin;
        this.gener = gener;
        this.image = image;
        this.isPending = isPending;
        this.accessToken = accessToken;
    }

    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Basic
    @Column(name = "uu_Id")
    public String getUuId() {
        return uuId;
    }

    public void setUuId(String uuId) {
        this.uuId = uuId;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Basic
    @Column(name = "age")
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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
    @Column(name = "type_Login")
    public String getTypeLogin() {
        return typeLogin;
    }

    public void setTypeLogin(String typeLogin) {
        this.typeLogin = typeLogin;
    }

    @Basic
    @Column(name = "gener")
    public String getGener() {
        return gener;
    }

    public void setGener(String gener) {
        this.gener = gener;
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
    @Column(name = "is_Pending")
    public String getIsPending() {
        return isPending;
    }

    public void setIsPending(String isPending) {
        this.isPending = isPending;
    }

    @Basic
    @Column(name = "access_Token")
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }


}
