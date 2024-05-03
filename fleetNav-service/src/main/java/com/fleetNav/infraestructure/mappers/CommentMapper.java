package com.fleetNav.infraestructure.mappers;

import com.fleetNav.api.dto.request.CommentRequest;
import com.fleetNav.api.dto.response.CommentResponse;
import com.fleetNav.domain.entities.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {TripMapper.class})
public interface CommentMapper {

    @Mapping(target = "id", ignore = true)
    Comment toComment(CommentRequest commentRequest);

    CommentResponse toCommentResponse(Comment comment);

    void updateFromCommentRequest(CommentRequest commentRequest, @MappingTarget Comment comment);
}
