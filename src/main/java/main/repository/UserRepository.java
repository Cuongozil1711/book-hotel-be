package main.repository;

import main.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, QueryByExampleExecutor<User> {
	/**
	 * @param username
	 * @return
	 */
	public User findByEmail(String username);

	public User findByPhoneAndIsPending(String phone, String isPending);

	public User findUserByUuId(String uid);
}
