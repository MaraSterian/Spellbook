package com.example.spellbook.service;

import com.example.spellbook.dto.CommentDTO;
import com.example.spellbook.mapper.CommentMapper;
import com.example.spellbook.model.Comment;
import com.example.spellbook.repository.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Autowired
    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    public List<CommentDTO> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream()
                .map(commentMapper::commentToCommentDTO)
                .collect(Collectors.toList());
    }

    public CommentDTO getCommentById(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));

        return commentMapper.commentToCommentDTO(comment);
    }

    public void createComment(CommentDTO commentDTO) {
        Comment comment = commentMapper.commentDTOToComment(commentDTO);
        commentRepository.save(comment);
    }

    public void updateComment(Long commentId, CommentDTO commentDTO) {
        Comment existingComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));

        // Update the existingEvent based on eventDTO
        existingComment.setContent(commentDTO.getContent());

        commentRepository.save(existingComment);
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    public List<CommentDTO> getCommentsByUserId(Long userId) {
        List<Comment> comments = commentRepository.findByUser_Id(userId);
        return comments.stream()
                .map(commentMapper::commentToCommentDTO)
                .collect(Collectors.toList());
    }

    public List<CommentDTO> getCommentsBySpellId(Long spellId) {
        List<Comment> comments = commentRepository.findBySpell_Id(spellId);
        return comments.stream()
                .map(commentMapper::commentToCommentDTO)
                .collect(Collectors.toList());
    }
}