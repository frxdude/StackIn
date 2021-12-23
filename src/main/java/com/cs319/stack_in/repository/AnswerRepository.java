package com.cs319.stack_in.repository;

import com.cs319.stack_in.entity.Answer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * AnswerRepository
 *
 * @author Ariunaa Gantumur
 **/
public interface AnswerRepository extends CrudRepository<Answer, Long> {

    Optional<Answer> findById(Long id);

    List<Answer> findAll();

    List<Answer> findAllByOrderByCreatedDate();

    List<Answer> findByQuestionId(Long id);

    List<Answer> findByUserId(Long id);


}
