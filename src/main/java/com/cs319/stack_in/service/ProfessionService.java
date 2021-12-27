package com.cs319.stack_in.service;

import com.cs319.stack_in.entity.Profession;
import com.cs319.stack_in.exception.BusinessException;

import java.util.List;

/**
 * JobsService
 *
 * @author Ariunaa Gantumur
 **/
public interface ProfessionService {

    List<Profession> getChildProfessions(Long parentId)  throws BusinessException;

    List<Profession> getRootProfessions();

    List<Profession> searchByName(String name);

}
