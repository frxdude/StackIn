package com.cs319.stack_in.repository;

import com.cs319.stack_in.entity.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends CrudRepository<Question, Long> {

    Optional<Question> findById(Long id);

    List<Question> findAll();

    List<Question> findAllByOrderByCreatedDate();

    List<Question> findByUserId(Long id);
}
