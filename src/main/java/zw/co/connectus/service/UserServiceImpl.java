package zw.co.connectus.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import zw.co.connectus.dal.entity.User;
import zw.co.connectus.dal.repository.UserRepository;
import zw.co.connectus.service.mapper.DtoMapper;
import zw.co.connectus.service.model.*;

import javax.xml.ws.http.HTTPException;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl {

	@Autowired
	DtoMapper mapper;

	@Value("${app.jwt.key:87b711b1-c005-4e8d-b0b0-819453ac577b}")
	private String jwtKey;

	@Autowired
	private UserRepository userRepository;

	public Optional<User> findByMsisdn(String msisdn) {

		return userRepository.findByMsisdn(msisdn);
	}

	public CheckDto check(String msisdn) {

		final Optional<User> byMsisdn = findByMsisdn(msisdn);
		if (byMsisdn.isPresent()) {
			final User user = byMsisdn.get();
			return new CheckDto(true, user.getId(), msisdn);
		}
		return new CheckDto(false, null, msisdn);
	}

	public AuthResponseDto signUp(UserDto userDto) {

		User user = userRepository.save(mapper.map(userDto));
		String token = generateJwt(user);
		return new AuthResponseDto(mapper.map(user), new JWT("Bearer", token, 31536000));
	}

	public AuthResponseDto signIn(SignInDto signInDto) {

		final Optional<User> byMsisdn = userRepository.findByMsisdn(signInDto.getMsisdn());
		if (byMsisdn.isPresent()) {
			User user = byMsisdn.get();
			if (user.getPassword().equals(signInDto.getPassword())) {
				String token = generateJwt(user);
				return new AuthResponseDto(mapper.map(user), new JWT("Bearer", token, 31536000));
			}
			throw new HTTPException(HttpStatus.UNAUTHORIZED.value());
		}
		throw new HTTPException(HttpStatus.NOT_FOUND.value());
	}

	public String generateJwt(User user) {

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, 1);
		return Jwts.builder()
				.setId(user.getId().toString())
				.claim("name", user.getFirstName() + " " + user.getLastName())
				.setIssuer("connectus")
				.claim("scope", "user")
				.setIssuedAt(new Date())
				.setExpiration(calendar.getTime())
				.signWith(
						SignatureAlgorithm.HS256,
						jwtKey
				)
				.compact();
	}
}
