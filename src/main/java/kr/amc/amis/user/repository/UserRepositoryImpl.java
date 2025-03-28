package kr.amc.amis.user.repository;

import kr.amc.amis.user.application.interfaces.UserRepository;
import kr.amc.amis.user.domain.User;
import kr.amc.amis.user.repository.entity.UserEntity;
import kr.amc.amis.user.repository.jpa.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public User save(User user) {
        UserEntity userEntity = new UserEntity(user);
        UserEntity entity = jpaUserRepository.save(userEntity);
        return entity.toUser();
    }

    @Override
    public User findById(Long userId) {
        return jpaUserRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("error")).toUser();
    }
}
