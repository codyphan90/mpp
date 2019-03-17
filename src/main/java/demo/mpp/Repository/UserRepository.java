package demo.mpp.Repository;
import demo.mpp.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	UserEntity findByUserNameEquals(String userName);
	Integer countByUserName(String userName);
	List<UserEntity> findAll();
}
