package kr.amc.amis.auth.application;

import kr.amc.amis.auth.application.dto.CreateUserAuthRequestDto;
import kr.amc.amis.auth.application.dto.LoginRequestDto;
import kr.amc.amis.auth.application.dto.UserAccessTokenResponseDto;
import kr.amc.amis.auth.application.interfaces.EmailVerificationRepository;
import kr.amc.amis.auth.application.interfaces.UserAuthRepository;
import kr.amc.amis.auth.domain.Email;
import kr.amc.amis.auth.domain.TokenProvider;
import kr.amc.amis.auth.domain.UserAuth;
import kr.amc.amis.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserAuthRepository userAuthRepository;
    private final EmailVerificationRepository emailVerificationRepository;
    private final TokenProvider tokenProvider;

    public Long registerUser(CreateUserAuthRequestDto dto) {
        Email email = Email.createEmail(dto.email());

        if (!emailVerificationRepository.isEmailVerified(email)) {
            throw new IllegalArgumentException("Email verification failed");
        }

        UserAuth userAuth = new UserAuth(dto.email(), dto.password(), dto.role());
        User user = new User(dto.name(), dto.profileImageUrl());
        userAuth = userAuthRepository.registerUser(userAuth, user);

        return userAuth.getUserId();
    }

    public UserAccessTokenResponseDto login(LoginRequestDto dto) {
        UserAuth userAuth = userAuthRepository.loginUser(dto.email(), dto.password());
        String token = tokenProvider.createToken(userAuth.getUserId(), userAuth.getUserRole());

        return new UserAccessTokenResponseDto(token);
    }
}
