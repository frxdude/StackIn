package com.cs319.stack_in.repository;

import com.cs319.stack_in.entity.Profession;
import com.cs319.stack_in.exception.BusinessException;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * ProfessionRepository
 *
 * @author Ariunaa Gantumur
 **/
public interface ProfessionRepository extends CrudRepository<Profession, Long> {

    List<Profession> findByParentProfession(Profession profession);

    List<Profession> findByParentProfessionIsNull();

    List<Profession> findByNameContains(String name);

}
