package zw.co.connectus.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import zw.co.connectus.dal.entity.User;
import zw.co.connectus.dal.repository.UserRepository;
import zw.co.connectus.service.mapper.DtoMapper;
import zw.co.connectus.service.model.AuthResponseDto;
import zw.co.connectus.service.model.CheckDto;
import zw.co.connectus.service.model.JWT;
import zw.co.connectus.service.model.UserDto;

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

	public AuthResponseDto signup(UserDto userDto) {

		User user = userRepository.save(mapper.map(userDto));
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, 1);
		String token = Jwts.builder()
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
		return new AuthResponseDto(mapper.mapUserToProfile(user), new JWT("Bearer", token, 31536000));
	}
}
