package kr.amc.amis.common.idenpotency;

public interface IdempotencyRepository {

    Idempotency getByKey(String key);
    void save(Idempotency idempotency);
}
