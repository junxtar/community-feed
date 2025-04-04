package kr.amc.amis.user.application.interfaces;

import kr.amc.amis.user.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRelationRepository {

    boolean isAlreadyFollow(User user, User targetUser);

    void save(User user, User targetUser);

    void delete(User user, User targetUser);
}
