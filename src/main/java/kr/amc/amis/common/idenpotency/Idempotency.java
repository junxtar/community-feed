package kr.amc.amis.common.idenpotency;

import kr.amc.amis.ui.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Idempotency {

    private final String key;
    private final Response<?> response;
}
