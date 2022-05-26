package main.repository;

import main.entity.UserHotel;
import main.entity.UserHotelLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLikeRepository extends JpaRepository<UserHotelLike, Integer>{
    public List<UserHotelLike> findAllByIdHotel(Integer idHotel);

    public List<UserHotelLike> findAllByIdUser(Integer idUser);
}

