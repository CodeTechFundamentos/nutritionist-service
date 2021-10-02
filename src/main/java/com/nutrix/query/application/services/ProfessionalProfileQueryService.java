package com.nutrix.query.application.services;

import com.nutrix.command.domain.ProfessionalProfile;
import com.nutrix.command.infra.IProfessionalProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProfessionalProfileQueryService extends ProfessionalProfile {

    @Autowired
    private IProfessionalProfileRepository professionalProfileRepository;


    @Override
    public List<ProfessionalProfile> getAll() throws Exception {
        return professionalProfileRepository.findAll();
    }

    @Override
    public Optional<ProfessionalProfile> getById(Integer id) throws Exception {
        return professionalProfileRepository.findById(id);
    }

    @Override
    public ProfessionalProfile findByNutritionist(Integer nutritionist_id) throws Exception{
        return professionalProfileRepository.findByNutritionist(nutritionist_id);
    }
}
