package com.cs319.stack_in.repository.redis;

import com.cs319.stack_in.entity.IndustryType;
import org.springframework.data.repository.CrudRepository;

/**
 * IndustryTypeRepository
 *
 * @author Ariunaa Gantumur
 **/
public interface IndustryTypeRepository extends CrudRepository<IndustryType, Long> {
}
