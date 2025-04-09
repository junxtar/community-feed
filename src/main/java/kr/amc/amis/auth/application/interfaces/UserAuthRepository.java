package kr.amc.amis.auth.application.interfaces;

import kr.amc.amis.auth.domain.UserAuth;
import kr.amc.amis.user.domain.User;

public interface UserAuthRepository {

    UserAuth registerUser(UserAuth userAuth, User user);
    UserAuth loginUser(String email, String password);
}
