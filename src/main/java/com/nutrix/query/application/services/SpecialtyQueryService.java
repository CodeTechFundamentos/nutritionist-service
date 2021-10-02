package com.nutrix.query.application.services;

import com.nutrix.command.domain.Specialty;
import com.nutrix.command.infra.ISpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SpecialtyQueryService extends Specialty {

    @Autowired
    private ISpecialtyRepository specialtyRepository;

    @Override
    public List<Specialty> getAll() throws Exception {
        return specialtyRepository.findAll();
    }

    @Override
    public Optional<Specialty> getById(Integer id) throws Exception {
        return specialtyRepository.findById(id);
    }

    @Override
    public List<Specialty> findByInstitutionName(String institution_name) throws Exception {
        return specialtyRepository.findByInstitutionName(institution_name);
    }

    @Override
    public Specialty findByNameAndInstitution(String name, String institution_name) throws Exception{
        return specialtyRepository.findByNameAndInstitutionName(name, institution_name);
    }
}