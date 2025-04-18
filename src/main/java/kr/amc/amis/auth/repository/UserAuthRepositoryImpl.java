package kr.amc.amis.auth.repository;

import kr.amc.amis.auth.application.interfaces.UserAuthRepository;
import kr.amc.amis.auth.domain.UserAuth;
import kr.amc.amis.auth.repository.entity.UserAuthEntity;
import kr.amc.amis.auth.repository.jpa.JpaUserAuthRepository;
import kr.amc.amis.message.repository.JpaFcmTokenRepository;
import kr.amc.amis.message.repository.entity.FcmTokenEntity;
import kr.amc.amis.user.application.interfaces.UserRepository;
import kr.amc.amis.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserAuthRepositoryImpl implements UserAuthRepository {

    private final JpaUserAuthRepository jpaUserAuthRepository;
    private final UserRepository userRepository;
    private final JpaFcmTokenRepository tokenRepository;

    @Override
    public UserAuth registerUser(UserAuth userAuth, User user) {
        User savedUser = userRepository.save(user);
        UserAuthEntity userAuthEntity = new UserAuthEntity(userAuth, savedUser.getId());
        userAuthEntity = jpaUserAuthRepository.save(userAuthEntity);
        return userAuthEntity.toUserAuth();
    }

    @Override
    @Transactional
    public UserAuth loginUser(String email, String password, String fcmToken) {
        UserAuthEntity userAuthEntity = jpaUserAuthRepository.findById(email).orElseThrow();
        UserAuth userAuth = userAuthEntity.toUserAuth();
        if (!userAuth.matchPassword(password)) {
            throw new IllegalArgumentException("Wrong password");
        }
        userAuthEntity.updateLastLoginDt();
        tokenRepository.save(new FcmTokenEntity(userAuth.getUserId(), fcmToken));

        return userAuth;
    }

}
