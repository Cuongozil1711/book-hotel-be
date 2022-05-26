package main.repository;

import main.entity.User;
import main.entity.UserHotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserHotelRepository extends JpaRepository<UserHotel, Integer>, QueryByExampleExecutor<UserHotel> {
    List<UserHotel> findUserHotelByIdHotel(Integer idHotel);
}
