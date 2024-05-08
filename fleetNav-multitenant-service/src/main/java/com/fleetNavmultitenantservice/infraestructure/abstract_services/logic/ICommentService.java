package com.fleetNavmultitenantservice.infraestructure.abstract_services.logic;

import com.fleetNavmultitenantservice.api.dto.request.CommentRequest;
import com.fleetNavmultitenantservice.api.dto.response.CommentResponse;

import java.util.UUID;

public interface ICommentService extends CrudService<CommentRequest, CommentResponse, UUID> {
}
