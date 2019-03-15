package demo.mpp.Repository.social;

import demo.mpp.Entity.social.FriendShipEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendShipRepository extends JpaRepository<FriendShipEntity, Integer> {
    FriendShipEntity findByIdEquals(Integer Id);
    List<FriendShipEntity> findAllByUserIdEquals(Integer userId);
}
