package com.cs319.stack_in.repository;

import com.cs319.stack_in.entity.Question;
import com.cs319.stack_in.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * UserRepository
 *
 * @author Sainjargal Ishdorj
 **/

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findById(String uniqueId);
    Optional<User>  findByEmail(String email);
    Optional<User>  findByUsername(String name);

    Optional<User> findByUsername(String email);

}
