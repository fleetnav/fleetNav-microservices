package com.fleetNav.infraestructure.mappers;

import com.fleetNav.api.dto.request.CommentRequest;
import com.fleetNav.api.dto.response.CommentResponse;
import com.fleetNav.domain.entities.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {CommentMapper.class})
public interface CommentMapper {
    default UUID mapCommentToUUID(Comment comment) {
        return comment != null ? comment.getId() : null;
    }

    Comment toComment(CommentRequest commentRequest);
    CommentResponse toCommentResponse(Comment comment);
    void updateFromCommentRequest(CommentRequest commentRequest, @MappingTarget Comment comment);
}
