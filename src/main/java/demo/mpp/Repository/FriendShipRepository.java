package demo.mpp.Repository;

import demo.mpp.Entity.FriendShipEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendShipRepository extends JpaRepository<FriendShipEntity, Integer> {
    List<FriendShipEntity> findAllByUserNameEquals(String userName);
    List<FriendShipEntity> findAll();
    FriendShipEntity findByUserNameEqualsAndAndRelateUserNameEquals(String userName, String relateUserName);
}
