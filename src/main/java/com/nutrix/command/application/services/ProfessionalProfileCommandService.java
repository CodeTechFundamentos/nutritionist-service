package com.nutrix.command.application.services;

import com.nutrix.command.domain.ProfessionalProfile;
import com.nutrix.command.infra.IProfessionalProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ProfessionalProfileCommandService extends ProfessionalProfile {

    @Autowired
    private IProfessionalProfileRepository professionalProfileRepository;

    @Override
    @Transactional
    public ProfessionalProfile save(ProfessionalProfile professionalprofile) throws Exception {
        return professionalProfileRepository.save(professionalprofile);
    }

    @Override
    @Transactional
    public void delete(Integer id) throws Exception {
        professionalProfileRepository.deleteById(id);
    }
}
