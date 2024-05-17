package com.fleetNav.service.infraestructure.abstract_services;

import com.fleetNav.service.api.dto.request.CommentRequest;
import com.fleetNav.service.api.dto.response.CommentResponse;

import java.util.UUID;


public interface ICommentService extends CreateReadDeleteService<CommentRequest, CommentResponse, UUID>, UpdateService<CommentRequest, CommentResponse, UUID> {
}
