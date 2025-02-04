package rimenergyapi.security;

import java.util.Base64;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import rimenergyapi.DataSourceBasedMultiTenantConnectionProviderImpl;
import rimenergyapi.security.dto.UserInfoDto;
import rimenergyapi.security.entity.User;

@Component
public class JwtTokenProvider {

	@Value("${security.jwt.token.secret-key:secret-key}")
	private String secretKey;

	@Value("${security.jwt.token.expire-length:3600000}")
	private long validityInMilliseconds = 3600000; // 100h

	@Value("${token.prefix}")
	private String tokenPrefix;

	@Value("${token.header}")
	private String tokenHeader;

	@Autowired
	private MyUserDetails myUserDetails;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private DataSourceBasedMultiTenantConnectionProviderImpl dataSourceBasedMultiTenantConnectionProvider;

		
	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	public String createToken(User user) {

		Claims claims = Jwts.claims().setSubject(user.getUsername());

		UserInfoDto result = this.modelMapper.map(user, UserInfoDto.class);

		// TODO : delete isROOT
		result.setRoot(true);

		claims.put("user", result);
		claims.put("userid", result.getId());
		// claims.put("subUser", -1);
		Date now = new Date();
		Date validity = new Date(now.getTime() + validityInMilliseconds);

		return Jwts.builder()//
				.setClaims(claims)//
				.setIssuedAt(now)//
				.setExpiration(validity)//
				.signWith(SignatureAlgorithm.HS256, secretKey)//
				.compact();
	}

	public Authentication getAuthentication(String token) {
		UserDetails userDetails = myUserDetails.loadUserByUsername(getUsername(token));
		dataSourceBasedMultiTenantConnectionProvider.addDataSource(getUserIdFromToken(token)+"");
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	public String getUsername(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}

	public Integer getUserIdFromToken(String token) {
		Integer id;
		try {
			final Claims claims = getClaimsFromToken(token);
			id = (Integer) claims.get("userid");
		} catch (Exception e) {
			id = null;
		}
		return id;
	}

	public Integer getSubUserIdFromToken(String token) {
		Integer id;
		try {
			final Claims claims = getClaimsFromToken(token);
			id = (Integer) claims.get("subUser");
		} catch (Exception e) {
			id = null;
		}
		return id;
	}
	
	private Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader(tokenHeader);
		if (bearerToken != null && bearerToken.startsWith(tokenPrefix)) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}

	public String resolveToken(String token) {
		if (token != null && token.startsWith(tokenPrefix)) {
			return token.substring(7, token.length());
		}
		return null;
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}

}
