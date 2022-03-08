package zw.co.connectus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zw.co.connectus.dal.entity.User;
import zw.co.connectus.dal.repository.UserRepository;
import zw.co.connectus.service.model.CheckDto;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl {

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

}
