package com.cs319.stack_in.repository.redis;

import com.cs319.stack_in.entity.redis.OneTimePassword;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * OTPRepository
 *
 * @author Ishdorj Sainjargal
 **/

public interface OTPRepository extends CrudRepository<OneTimePassword, String> {

    Optional<OneTimePassword> findByTypeAndId(String type, String id);

}
