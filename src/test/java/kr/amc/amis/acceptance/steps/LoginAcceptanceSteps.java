package kr.amc.amis.acceptance.steps;

import io.restassured.RestAssured;
import kr.amc.amis.auth.application.dto.LoginRequestDto;
import kr.amc.amis.auth.application.dto.UserAccessTokenResponseDto;
import org.springframework.http.MediaType;

public class LoginAcceptanceSteps {

    public static String requestLoginGetToken(LoginRequestDto dto) {
        UserAccessTokenResponseDto res = getLoginResponse(dto);

        return res.accessToken();
    }

    public static Integer requestLoginGetResponseCode(LoginRequestDto dto) {
        return RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(dto)
                .when()
                .post("/login")
                .then()
                .extract()
                .jsonPath()
                .getObject("code", Integer.class);
    }

    private static UserAccessTokenResponseDto getLoginResponse(LoginRequestDto dto) {
        return RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(dto)
                .when()
                .post("/login")
                .then()
                .extract()
                .jsonPath()
                .getObject("value", UserAccessTokenResponseDto.class);
    }


}
