package com.cs319.stack_in.service;

import com.cs319.stack_in.entity.Profession;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * JobsService
 *
 * @author Ariunaa Gantumur
 **/
public interface ProfessionService {
    List<Profession> getProfessions(Long professionId);
    List<Profession> getRootProfessions();

}
