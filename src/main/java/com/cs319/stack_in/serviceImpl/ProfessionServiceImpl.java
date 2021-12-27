package com.cs319.stack_in.serviceImpl;

import com.cs319.stack_in.entity.Profession;
import com.cs319.stack_in.exception.BusinessException;
import com.cs319.stack_in.helper.Localization;
import com.cs319.stack_in.jwt.JwtTokenProvider;
import com.cs319.stack_in.repository.ProfessionRepository;
import com.cs319.stack_in.repository.UserRepository;
import com.cs319.stack_in.service.ProfessionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ProfessionServiceImpl
 *
 * @author Ariunaa Gantumur
 **/

@Service
public class ProfessionServiceImpl implements ProfessionService {

    Localization localization;
    ProfessionRepository repository;
    UserRepository userRepository;
    JwtTokenProvider jwtTokenProvider;

    public ProfessionServiceImpl(Localization localization, ProfessionRepository repository, UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.localization = localization;
        this.repository = repository;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public List<Profession> getRootProfessions() {
        List<Profession> professions = repository.findByParentProfessionIsNull();
        return professions;
    }

    @Override
    public List<Profession> searchByName(String name) {
        List<Profession> professions = repository.findByNameContains(name);
        professions.stream()
                .forEach(p -> p.setParentProfession(null));

        return professions;
    }

    @Override
    public List<Profession> getChildProfessions(Long parentId) throws BusinessException {
        Profession parentProfession = repository.findById(parentId)
                .orElseThrow(() -> new BusinessException(localization.getMessage("profession.not.found")));

        List<Profession> professions = repository.findByParentProfession(parentProfession);
        professions.stream().forEach(p -> p.setParentProfession(null));
        return professions;
    }






}
