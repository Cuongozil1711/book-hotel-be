package main.repository;

import main.entity.UserRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoomRepository extends JpaRepository<UserRoom, Integer> {

    List<UserRoom> findByIdUserAndIsDelete(Integer idUser, String isDelete);
}
