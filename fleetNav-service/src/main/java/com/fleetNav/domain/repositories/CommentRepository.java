package com.fleetNav.domain.repositories;

import com.fleetNav.domain.entities.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommentRepository extends CrudRepository<Comment,UUID> {
}
