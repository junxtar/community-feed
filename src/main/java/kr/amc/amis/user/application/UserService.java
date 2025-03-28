package kr.amc.amis.user.application;

import kr.amc.amis.user.application.dto.CreateUserRequestDto;
import kr.amc.amis.user.application.dto.GetUserResponseDto;
import kr.amc.amis.user.application.interfaces.UserRepository;
import kr.amc.amis.user.domain.User;
import kr.amc.amis.user.domain.UserInfo;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserRequestDto dto) {
        UserInfo userInfo = new UserInfo(dto.name(), dto.profileImageUrl());
        User user = new User(null, userInfo);
        return userRepository.save(user);
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId);
    }

    public GetUserResponseDto getUserProfile(Long userId) {
        User user = getUser(userId);
        return new GetUserResponseDto(user);
    }
}
