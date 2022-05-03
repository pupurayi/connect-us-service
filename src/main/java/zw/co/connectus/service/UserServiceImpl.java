package zw.co.connectus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.co.connectus.dal.entity.User;
import zw.co.connectus.dal.repository.UserRepository;
import zw.co.connectus.service.mapper.DtoMapper;
import zw.co.connectus.service.model.*;

import javax.xml.ws.http.HTTPException;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl {

    @Autowired
    private DtoMapper mapper;

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

    public ResponseEntity<UserDto> signIn(SignInDto signInDto) {

        final Optional<User> byMsisdn = userRepository.findByMsisdn(signInDto.getMsisdn());
        if (byMsisdn.isPresent()) {
            User user = byMsisdn.get();
            if (user.getPassword().equals(signInDto.getPassword())) {
                return ResponseEntity.ok(mapper.map(user));
            }
            throw new HTTPException(HttpStatus.UNAUTHORIZED.value());
        }
        throw new HTTPException(HttpStatus.NOT_FOUND.value());
    }

    public ResponseEntity<UserDto> createUser(UserDto userDto) {
        User user = userRepository.save(mapper.map(userDto));
        return ResponseEntity.ok(mapper.map(user));
    }

    public ResponseEntity<UserDto> getUserById(UUID userId) {
        Optional<User> byId = userRepository.findById(userId);
        if (byId.isPresent()) {
            return ResponseEntity.ok(mapper.map(byId.get()));
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<UserDto> put(UserDto userDto) {
        Optional<User> byId = userRepository.findById(userDto.getId());
        if (byId.isPresent()) {
            User user = userRepository.save(mapper.map(userDto));
            return ResponseEntity.ok(mapper.map(user));
        }
        return ResponseEntity.notFound().build();
    }
}
