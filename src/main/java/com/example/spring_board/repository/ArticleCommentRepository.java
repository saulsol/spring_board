package com.example.spring_board.repository;

import com.example.spring_board.domain.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {

}