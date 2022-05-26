package main.beans;

import main.entity.User;
import org.springframework.web.multipart.MultipartFile;

public class Userbean {
    private User user;
    private MultipartFile files;

    public Userbean() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MultipartFile getFiles() {
        return files;
    }

    public void setFiles(MultipartFile files) {
        this.files = files;
    }
}
