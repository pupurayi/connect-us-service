package zw.co.connectus.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.connectus.dal.entity.UserProductRating;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserProductRatingRepository extends JpaRepository<UserProductRating, UUID> {
    List<UserProductRating> findAllByUserIdAndLikedIsFalse(String userId);

    List<UserProductRating> findAllByUserIdOrderByUpdatedAsc(String userId);

    Optional<UserProductRating> findByUserIdAndProductId(String userId, String productId);
}
