package kr.amc.amis.post.domain.content;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class PostContentTest {

    @Test
    void givenContentLengthIsOk_whenCreated_thenReturnTextContent() {
        // given
        String text = "this is a test";

        // when
        PostContent postContent = new PostContent(text);

        // then
        assertEquals(text, postContent.getContentText());
    }

    @Test
    void givenContentLengthIsOver_whenCreated_thenReturnThrowError() {
        // given
        String content = "a".repeat(501);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁 닭 숡 굵 갉"})
    void givenContentLengthIsOverAndKorean_whenCreated_thenReturnThrowError(String koreanWord) {
        // given
        String content = koreanWord.repeat(501);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @Test
    void givenContentLengthIsUnder_whenCreated_thenReturnThrowError() {
        // given
        String content = "a".repeat(4);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentIsEmpty_whenCreated_thenReturnThrowError(String value) {
        // when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(value));
    }
}