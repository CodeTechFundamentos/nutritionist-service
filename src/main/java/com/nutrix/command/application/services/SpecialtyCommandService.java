package com.nutrix.command.application.services;

import com.nutrix.command.domain.Specialty;
import com.nutrix.command.infra.ISpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class SpecialtyCommandService extends Specialty {

    @Autowired
    private ISpecialtyRepository specialtyRepository;

    @Override
    @Transactional
    public Specialty save(Specialty specialty) throws Exception {
        return specialtyRepository.save(specialty);
    }

    @Override
    @Transactional
    public void delete(Integer id) throws Exception {
        specialtyRepository.deleteById(id);
    }
}