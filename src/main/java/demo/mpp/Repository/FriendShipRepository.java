package demo.mpp.Repository;

import demo.mpp.Entity.FriendShipEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendShipRepository extends JpaRepository<FriendShipEntity, Integer> {
    FriendShipEntity findByIdEquals(Integer Id);
    List<FriendShipEntity> findAllByUserIdEquals(Integer userId);
}
