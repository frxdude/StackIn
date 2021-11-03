package com.cs319.stack_in.repository;

import com.cs319.stack_in.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * UserRepository
 *
 * @author Sainjargal Ishdorj
 **/

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUniqueId(String uniqueId);

    Optional<User> findByUsername(String email);

}
