package kr.amc.amis.common.idenpotency.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.amc.amis.common.idenpotency.Idempotency;
import kr.amc.amis.common.utils.ResponseObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "community_idempotency")
@NoArgsConstructor
@AllArgsConstructor
public class IdempotencyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String idempotencyKey;

    @Getter
    @Column(nullable = false)
    private String response;

    public IdempotencyEntity(Idempotency idempotency) {
        this.idempotencyKey = idempotency.getKey();
        this.response = ResponseObjectMapper.toString(idempotency.getResponse());
    }

    public Idempotency toIdempotency() {
        return new Idempotency(this.idempotencyKey, ResponseObjectMapper.toResponseObject(this.response));
    }
}
