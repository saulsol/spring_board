package com.example.spring_board.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@ToString
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditingFields {

    // 엔티티 인덱싱은 하위 클래스에서 한다.


    // 엔티티 공통 속성 추상화
    // meta data
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt; //생성 일시

    @CreatedBy
    @Column(nullable = false, updatable = false, length = 100)
    private String createdBy; // 생성자

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedAt; // 수정 일시

    @LastModifiedBy
    @Column(nullable = false, length = 100)
    private String modifiedBy;
    // 수정자, 업데이트가 매번 일어나는 컬럼 값이라서
    // updatable = false 를 달지 않는다.
}