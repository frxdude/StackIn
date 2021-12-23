package com.cs319.stack_in.repository;

import com.cs319.stack_in.entity.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * CommentRepository
 *
 * @author Ariunaa Gantumur
 **/
public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findByRefId(Long refId);

    void deleteByRefId(Long refId);

    void deleteById(Long id);
}
