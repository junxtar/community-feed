package kr.amc.amis.user.application.interfaces;

import kr.amc.amis.user.domain.User;

public interface UserRepository {

    User save(User user);
    User findById(Long userId);
}
