package com.cs319.stack_in.serviceImpl;

import com.cs319.stack_in.entity.Profession;
import com.cs319.stack_in.helper.Localization;
import com.cs319.stack_in.jwt.JwtTokenProvider;
import com.cs319.stack_in.repository.ProfessionRepository;
import com.cs319.stack_in.repository.QuestionRepository;
import com.cs319.stack_in.repository.UserRepository;
import com.cs319.stack_in.service.ProfessionService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * ProfessionServiceImpl
 *
 * @author Ariunaa Gantumur
 **/
public class ProfessionServiceImpl implements ProfessionService {



    Localization localization;
    ProfessionRepository repository;
    UserRepository userRepository;
    JwtTokenProvider jwtTokenProvider;

    @Override
    public List<Profession> getProfessions(Long professionId) {

        List<Profession> professions = repository.findByParentId(professionId);

        return professions;
    }


    public List<Profession> getRootProfessions(){
        List<Profession> professions = repository.findByParentIdAndParentIdIsNull();
        return professions;


    }

}
