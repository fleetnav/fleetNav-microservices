package com.fleetNav.infraestructure.services;

import com.fleetNav.api.dto.request.CommentRequest;
import com.fleetNav.api.dto.response.CommentResponse;
import com.fleetNav.domain.entities.Comment;
import com.fleetNav.domain.entities.Trip;
import com.fleetNav.domain.repositories.CommentRepository;
import com.fleetNav.domain.repositories.TripRepository;
import com.fleetNav.infraestructure.abstract_services.ICommentService;
import com.fleetNav.infraestructure.mappers.CommentMapper;
import lombok.AllArgsConstructor;
import com.fleetNav.util.exceptions.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.UUID;


@Service
@AllArgsConstructor
public class CommentService implements ICommentService {

    @Autowired
    private final CommentRepository commentRepository;

    @Autowired
    private final TripRepository tripRepository;
    @Autowired
    private final CommentMapper commentMapper;

    @Override
    public CommentResponse create(CommentRequest commentRequest) {
        Comment comment = commentMapper.toComment(commentRequest);
        Trip trip = tripRepository.findById(commentRequest.getTripId())
                .orElseThrow(() -> new IdNotFoundException("TRIP", commentRequest.getTripId()));

        comment.setTrip(trip);
        Comment saveComment = commentRepository.save(comment);
        return commentMapper.toCommentResponse(saveComment);
    }

    @Override
    public CommentResponse update(UUID id, CommentRequest commentRequest) {
        Comment existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("COMMENT", id));

        commentMapper.updateFromCommentRequest(commentRequest, existingComment);
        Comment updateComment = commentRepository.save(existingComment);
        return commentMapper.toCommentResponse(updateComment);
    }

    @Override
    public void delete(UUID uuid) {
        commentRepository.deleteById(uuid);
    }

    @Override
    public Page<CommentResponse> getAll(Pageable pageable) {
        Page<Comment> commentPage = commentRepository.findAll(pageable);
        return commentPage.map(commentMapper::toCommentResponse);
    }

    @Override
    public Optional<CommentResponse> getById(UUID uuid) {
        Optional<Comment> comment = commentRepository.findById(uuid);
        if (comment.isEmpty()) throw new IdNotFoundException("COMMENT", uuid);
        return comment.map(commentMapper::toCommentResponse);
    }
}
