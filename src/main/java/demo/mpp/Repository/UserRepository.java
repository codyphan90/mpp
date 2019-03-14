package demo.mpp.Repository;
import demo.mpp.Entity.UserEntity;
import survey.demo.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	UserEntity findByUserNameEquals(String userName);
	Integer countByUserName(String userName);
}
