package zw.co.connectus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.co.connectus.dal.entity.User;
import zw.co.connectus.dal.entity.UserProductRating;
import zw.co.connectus.dal.repository.UserProductRatingRepository;
import zw.co.connectus.dal.repository.UserRepository;
import zw.co.connectus.service.mapper.DtoMapper;
import zw.co.connectus.service.model.*;

import javax.xml.ws.http.HTTPException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl {

    @Autowired
    private DtoMapper mapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProductRatingRepository userProductRatingRepository;

    public Optional<User> findByMsisdn(String msisdn) {

        return userRepository.findByMsisdn(msisdn);
    }

    public Optional<User> findById(UUID userId) {

        return userRepository.findById(userId);
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


    public ResponseEntity resetPassword(String msisdn) {

        final Optional<User> byMsisdn = userRepository.findByMsisdn(msisdn);
        if (byMsisdn.isPresent()) {
            User user = byMsisdn.get();
            String password = UUID.randomUUID().toString().substring(0, 7);
            user.setPassword(password);
            userRepository.save(user);
            // TODO send email
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
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
            User user = byId.get();
            user.setEmail(userDto.getEmail());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setAvatar(userDto.getAvatar());
            user.setGeofenceRange(userDto.getGeofenceRange());
            user.setGender(userDto.getGender());
            user.setDob(userDto.getDob());
            user.setEthnicity(userDto.getEthnicity());
            user.setReligion(userDto.getReligion());
            user.setTownship(userDto.getTownship());
            user.setTown(userDto.getTown());
            user.setPassword(userDto.getPassword());
            user = userRepository.save(user);
            return ResponseEntity.ok(mapper.map(user));
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<String> rateProducts(UUID userId, Map<UUID, Boolean> ratings) {
        Optional<User> byId = userRepository.findById(userId);
        if (byId.isPresent()) {
            for (var entry : ratings.entrySet()) {
                Optional<UserProductRating> byUserIdAndProductId = userProductRatingRepository.findByUserIdAndProductId(userId.toString(), entry.getKey().toString());
                UserProductRating userProductRating;
                if (byUserIdAndProductId.isPresent()) {
                    userProductRating = byUserIdAndProductId.get();
                } else {
                    userProductRating = new UserProductRating();
                    userProductRating.setUserId(userId.toString());
                    userProductRating.setProductId(entry.getKey().toString());
                }
                userProductRating.setLiked(entry.getValue());
                userProductRatingRepository.save(userProductRating);
            }
        }
        return ResponseEntity.ok("Complete");
    }

    public ResponseEntity<List<UserDto>> getServiceProviders(UUID userId) {
        List<UserDto> users = userRepository.findAll()
                .stream()
                .map(mapper::map)
                .filter(userDto -> !userDto.getId().equals(userId))
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }
}