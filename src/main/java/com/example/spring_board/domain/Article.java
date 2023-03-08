package com.example.spring_board.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.*;


@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
} )
@Entity
@NoArgsConstructor
@Getter
public class Article extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Article_id")
    private Long id;

    @ToString.Exclude // 순환참조 방지
    @OrderBy("id")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();
    // 중복 허용하지 않기 위해서 SET 사용

    // not NULL
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, length = 10000)
    private String content; // 본문

    // Optional
    private String hashtag;



    @Builder
    public Article(String title, String content, String hashtag){
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }


    // 동일성, 동등성 검사
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Article 체크
        if (!(o instanceof Article article)) return false; // pattern variable
        return id != null && id.equals(article.id);
        // 영속화되기 전에는 id 값이 없다. 따라서 id 값도 체크
        // 새로 만든 엔티티들이 영속화가 되지 않았다면 동등성 검사 탈락
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
