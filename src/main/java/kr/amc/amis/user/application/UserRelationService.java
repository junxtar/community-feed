package kr.amc.amis.user.application;

import kr.amc.amis.user.application.dto.FollowUserRequestDto;
import kr.amc.amis.user.application.interfaces.UserRelationRepository;
import kr.amc.amis.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class UserRelationService {

    private final UserService userService;
    private final UserRelationRepository userRelationRepository;

    public UserRelationService(UserService userService,
            UserRelationRepository userRelationRepository) {
        this.userService = userService;
        this.userRelationRepository = userRelationRepository;
    }

    public void follow(FollowUserRequestDto dto) {
        User user = userService.getUser(dto.userId());
        User targetUser = userService.getUser(dto.targetUserId());

        if (userRelationRepository.isAlreadyFollow(user, targetUser)) {
            throw new IllegalArgumentException("user is already follow");
        }

        user.follow(targetUser);
        userRelationRepository.save(user, targetUser);
    }

    public void unFollow(FollowUserRequestDto dto) {
        User user = userService.getUser(dto.userId());
        User targetUser = userService.getUser(dto.targetUserId());

        if (!userRelationRepository.isAlreadyFollow(user, targetUser)) {
            throw new IllegalArgumentException("user is already follow");
        }

        user.unFollow(targetUser);
        userRelationRepository.delete(user, targetUser);
    }
}
