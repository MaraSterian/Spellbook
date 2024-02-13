package com.example.spellbook.repository;

import com.example.spellbook.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByUser_Id(Long userId);
    List<Comment> findBySpell_Id(Long spellId);
}
