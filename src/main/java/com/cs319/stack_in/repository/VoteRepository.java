package com.cs319.stack_in.repository;

import com.cs319.stack_in.entity.Vote;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * VoteRepository
 *
 * @author Ariunaa Gantumur
 **/
public interface VoteRepository extends CrudRepository<Vote, Long> {

    Optional<Vote> findByUserIdAndRefId(Long userId, Long refId);

}
