package kr.amc.amis.common.idenpotency.repository;

import java.util.Optional;
import kr.amc.amis.common.idenpotency.repository.entity.IdempotencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaIdempotencyRepository extends JpaRepository<IdempotencyEntity, Long> {

    Optional<IdempotencyEntity> findByIdempotencyKey(String key);
}
