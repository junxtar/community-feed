package kr.amc.amis.acceptance.steps;

import io.restassured.RestAssured;
import kr.amc.amis.auth.application.dto.CreateUserAuthRequestDto;
import kr.amc.amis.auth.application.dto.SendEmailRequestDto;
import org.springframework.http.MediaType;

public class SignUpAcceptanceSteps {

    public static Integer requestSendEmail(SendEmailRequestDto dto) {
        return RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(dto)
                .when()
                .post("/signup/send-verification-email")
                .then()
                .extract()
                .jsonPath().get("code");
    }

    public static Integer requestVerifyEmail(String email, String token) {
        return RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .queryParam("email", email)
                .queryParam("token", token)
                .when()
                .get("signup/verify-token")
                .then()
                .extract()
                .jsonPath().get("code");
    }

    public static Integer registerUser(CreateUserAuthRequestDto dto) {
        return RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(dto)
                .when()
                .post("/signup/register")
                .then()
                .extract()
                .jsonPath().get("code");
    }
}
