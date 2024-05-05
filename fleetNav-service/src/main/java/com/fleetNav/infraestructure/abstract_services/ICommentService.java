package com.fleetNav.infraestructure.abstract_services;

import com.fleetNav.api.dto.request.CommentRequest;
import com.fleetNav.api.dto.response.CommentResponse;

import java.util.UUID;

public interface ICommentService extends CrudService<CommentRequest, CommentResponse, UUID> {
}
