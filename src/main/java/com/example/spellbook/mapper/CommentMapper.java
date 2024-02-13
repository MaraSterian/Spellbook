package com.example.spellbook.mapper;

import com.example.spellbook.dto.CommentDTO;
import com.example.spellbook.model.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    SpellMapper spellMapper;
    public CommentDTO commentToCommentDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setContent(comment.getContent());

        commentDTO.setUser(UserMapper.userToUserDTO(comment.getUser()));
        if (comment.getSpell() != null) {
            commentDTO.setSpell(spellMapper.spellToSpellDTO(comment.getSpell()));
        }
        return commentDTO;
    }

    public Comment commentDTOToComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setContent(commentDTO.getContent());

        comment.setUser(UserMapper.userDTOToUser(commentDTO.getUser()));
        if (commentDTO.getSpell() != null) {
            comment.setSpell(spellMapper.spellDTOToSpell(commentDTO.getSpell()));
        }

        return comment;
    }
}
