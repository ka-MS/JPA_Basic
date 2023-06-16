package org.example.hellojpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
@MappedSuperclass // 공통의 속성이 있을때 사용 엔티티x 테이블 매핑x 직접생성해서 사용하지 않도록 추상클래스 권장
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity {

    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;

}
