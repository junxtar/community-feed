package kr.amc.amis.common.idenpotency.repository;

import kr.amc.amis.common.idenpotency.Idempotency;
import kr.amc.amis.common.idenpotency.IdempotencyRepository;
import kr.amc.amis.common.idenpotency.repository.entity.IdempotencyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class IdempotencyRepositoryImpl implements IdempotencyRepository {

    private final JpaIdempotencyRepository jpaIdempotencyRepository;

    @Override
    public Idempotency getByKey(String key) {
        return jpaIdempotencyRepository.findByIdempotencyKey(key)
                .map(IdempotencyEntity::toIdempotency)
                .orElse(null);
    }

    @Override
    public void save(Idempotency idempotency) {
        jpaIdempotencyRepository.save(new IdempotencyEntity(idempotency));
    }
}
