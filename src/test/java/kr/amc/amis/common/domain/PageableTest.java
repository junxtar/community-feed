package kr.amc.amis.common.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PageableTest {

    @Test
    void givenPageableIndexIsNull_whenGetOffset_thenShouldBeReturn0() {
        // given
        Pageable pageable = new Pageable();

        // when
        int offset = pageable.getOffset();
        int limit = pageable.getLimit();

        // then
        Assertions.assertEquals(0, offset);
        Assertions.assertEquals(10, limit);
    }

    @Test
    void givenPageableIndexIs2Size10_whenGetOffset_thenShouldBeReturn10() {
        // given
        Pageable pageable = new Pageable(2, 10);

        // when
        int offset = pageable.getOffset();
        int limit = pageable.getLimit();

        // then
        Assertions.assertEquals(10, offset);
        Assertions.assertEquals(10, limit);
    }
}
