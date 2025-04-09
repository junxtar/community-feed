package kr.amc.amis.auth.ui;

import kr.amc.amis.auth.application.AuthService;
import kr.amc.amis.auth.application.dto.LoginRequestDto;
import kr.amc.amis.auth.application.dto.UserAccessTokenResponseDto;
import kr.amc.amis.ui.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final AuthService authService;

    @PostMapping
    public Response<UserAccessTokenResponseDto> login(
            @RequestBody LoginRequestDto loginRequestDto) {
        return Response.ok(authService.login(loginRequestDto));
    }

}
