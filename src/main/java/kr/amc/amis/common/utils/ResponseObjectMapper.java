package kr.amc.amis.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.amc.amis.ui.Response;

public class ResponseObjectMapper {

    private ResponseObjectMapper() {}

    private final static ObjectMapper mapper = new ObjectMapper();

    public static Response toResponseObject(String response) {
        try {
            return mapper.readValue(response, Response.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static String toString(Response<?> response) {
        try {
            return mapper.writeValueAsString(response);
        } catch (Exception e) {
            return null;
        }
    }
}
