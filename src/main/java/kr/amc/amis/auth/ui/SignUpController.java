package kr.amc.amis.auth.ui;

import kr.amc.amis.auth.application.AuthService;
import kr.amc.amis.auth.application.EmailService;
import kr.amc.amis.auth.application.dto.CreateUserAuthRequestDto;
import kr.amc.amis.auth.application.dto.SendEmailRequestDto;
import kr.amc.amis.ui.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {

    private final EmailService emailService;
    private final AuthService authService;

    @PostMapping("/send-verification-email")
    public Response<Void> sendEmail(@RequestBody SendEmailRequestDto dto) {
        emailService.sendEmail(dto);
        return Response.ok(null);
    }

    @GetMapping("verify-token")
    public Response<Void> verifyEmail(@RequestParam("token") String token, @RequestParam("email") String email) {
        emailService.verifyEmail(email, token);
        return Response.ok(null);
    }

    @PostMapping("/register")
    public Response<Long> register(@RequestBody CreateUserAuthRequestDto dto) {
        Long userId = authService.registerUser(dto);
        return Response.ok(userId);
    }

}
