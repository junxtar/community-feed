package kr.amc.amis.user.ui;

import java.util.List;
import kr.amc.amis.ui.Response;
import kr.amc.amis.user.application.UserService;
import kr.amc.amis.user.application.dto.CreateUserRequestDto;
import kr.amc.amis.user.application.dto.GetUserListResponseDto;
import kr.amc.amis.user.application.dto.GetUserResponseDto;
import kr.amc.amis.user.domain.User;
import kr.amc.amis.user.repository.jpa.JpaUserListQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JpaUserListQueryRepository jpaUserListQueryRepository;
    @PostMapping
    public Response<Long> createUser(@RequestBody CreateUserRequestDto dto) {
        User user = userService.createUser(dto);
        return Response.ok(user.getId());
    }

    @GetMapping("/{userId}/following")
    public Response<List<GetUserListResponseDto>> getFollowingList(@PathVariable(name = "userId") Long userId) {
        List<GetUserListResponseDto> result = jpaUserListQueryRepository.getFollwingUserList(
                userId);
        return Response.ok(result);
    }

    @GetMapping("/{userId}/follower")
    public Response<List<GetUserListResponseDto>> getFollowerList(@PathVariable(name = "userId") Long userId) {
        List<GetUserListResponseDto> result = jpaUserListQueryRepository.getFollwerUserList(
                userId);
        return Response.ok(result);
    }

    @GetMapping("/{userId}")
    public Response<GetUserResponseDto> getProfile(@PathVariable(name = "userId") Long userId) {
        GetUserResponseDto userProfile = userService.getUserProfile(userId);
        return Response.ok(userProfile);
    }

}
