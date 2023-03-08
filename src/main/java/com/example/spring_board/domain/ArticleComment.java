package com.example.spring_board.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Objects;

@Table( name = "Comment",
        indexes = {
                @Index(columnList = "content"),
                @Index(columnList = "createdAt"),
                @Index(columnList = "createdBy")
        } )
@Entity
@NoArgsConstructor
@Getter
public class ArticleComment extends AuditingFields{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Comment_id")
    private Long id;

    @ManyToOne(optional = false) //optional = false -> 필수값
    @JoinColumn(name = "Article_id")
    private Article article; // 게시글 ID

    @Column(nullable = false, length = 500)
    private String content;


    // 연관관계 편의 메소드
    public void addComment(Article article){
        article.getArticleComments().add(this);
        this.article = article;
    }

    @Builder
    public ArticleComment(Article article, String content){ // 생성자에서 연관관계 편의 메소드 바로 사용
        addComment(article);
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleComment that)) return false;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}