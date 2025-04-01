package kr.amc.amis.acceptance.steps;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kr.amc.amis.post.application.dto.LikeRequestDto;
import org.springframework.http.MediaType;

public class LikeAcceptanceSteps {

    public static ExtractableResponse<Response> likePost(LikeRequestDto dto) {
        return RestAssured
                .given()
                .body(dto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .post("/post/like")
                .then()
                .extract();
    }

}
