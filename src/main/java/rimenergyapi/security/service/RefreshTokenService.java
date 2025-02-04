package rimenergyapi.security.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rimenergyapi.security.entity.RefreshToken;
import rimenergyapi.security.exception.TokenRefreshException;
import rimenergyapi.security.repository.RefreshTokenRepository;
import rimenergyapi.security.repository.UserRepository;

@Service
public class RefreshTokenService {
	@Value("${security.jwt.RefreshExpirationMs}")
	private Long refreshTokenDurationMs;

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@Autowired
	private UserRepository userRepository;

	public Optional<RefreshToken> findByToken(String token) {
		return refreshTokenRepository.findByToken(token);
	}

	@Transactional(transactionManager = "masterTransactionManager")
	public RefreshToken createRefreshToken(int userId) {
		RefreshToken refreshToken = new RefreshToken();

		refreshToken.setUser(userRepository.findById(userId));
		refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
		refreshToken.setToken(UUID.randomUUID().toString());

		refreshToken = refreshTokenRepository.save(refreshToken);
		return refreshToken;
	}

	@Transactional(transactionManager = "masterTransactionManager")
	public RefreshToken verifyExpiration(RefreshToken token) {
		if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
			refreshTokenRepository.delete(token);
			throw new TokenRefreshException(token.getToken(),
					"Refresh token was expired. Please make a new signin request");
		}
		return token;
	}

	@Transactional(transactionManager = "masterTransactionManager")
	public int deleteByUserId(int userId) {
		return refreshTokenRepository.deleteByUser(userRepository.getOne(userId));
	}
	
	@Transactional(transactionManager = "masterTransactionManager")
	public void updateUserIdByToken(String token, int userId) {
		 refreshTokenRepository.setUserId(userId);

	}
}
