package survey.demo.Repository;
import survey.demo.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	UserEntity findByUserNameEquals(String username);
}
