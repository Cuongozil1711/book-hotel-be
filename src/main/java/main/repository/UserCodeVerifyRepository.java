package main.repository;

import main.entity.UserCodeVerify;
import main.entity.UserHotelLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCodeVerifyRepository extends JpaRepository<UserCodeVerify, Integer> {
}
