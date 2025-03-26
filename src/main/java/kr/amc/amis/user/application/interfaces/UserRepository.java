package kr.amc.amis.user.application.interfaces;

import java.util.Optional;
import kr.amc.amis.user.domain.User;

public interface UserRepository {

    User save(User user);
    Optional<User> findById(Long userId);
}
