package com.cs319.stack_in.repository;

import com.cs319.stack_in.entity.Profession;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * ProfessionRepository
 *
 * @author Ariunaa Gantumur
 **/
public interface ProfessionRepository extends CrudRepository<Profession, Long> {
    List<Profession> findByParentId(Long parentId);
    List<Profession> findByParentIdAndParentIdIsNull();

}
