package rimenergyapi.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rimenergyapi.security.entity.RefreshToken;
import rimenergyapi.security.entity.User;


@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
  Optional<RefreshToken> findByToken(String token);

  @Modifying
  int deleteByUser(User user);
  
  @Modifying
  @Query(value = "update refreshtoken set user_id = ?1", nativeQuery = true)
  void setUserId(int userId);
  
}
