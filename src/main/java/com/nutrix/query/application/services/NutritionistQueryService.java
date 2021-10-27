package com.nutrix.query.application.services;

import com.nutrix.command.domain.Nutritionist;
import com.nutrix.command.infra.INutritionistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class NutritionistQueryService extends Nutritionist {

    @Autowired
    private INutritionistRepository nutritionistRepository;

    @Override
    public List<Nutritionist> getAll() throws Exception {
        return nutritionistRepository.findAll();
    }

    @Override
    public Optional<Nutritionist> getById(Integer id) throws Exception {
        return nutritionistRepository.findById(id);
    }

    @Override
    public Nutritionist findByUsername(String username) throws Exception {
        return nutritionistRepository.findByUsername(username);
    }

    @Override
    public Nutritionist findByCnpNumber(Integer cnp_number) throws Exception {
        return nutritionistRepository.findByCnpNumber(cnp_number);
    }

    @Override
    public List<Nutritionist> findByFirstName(String firstname) throws Exception {
        return nutritionistRepository.findByFirstName(firstname);
    }

    @Override
    public List<Nutritionist> findByLastName(String lastname) throws Exception {
        return nutritionistRepository.findByLastName(lastname);
    }

    @Override
    public List<Nutritionist> findByFirstNameAndLastName(String firstname, String lastname) throws Exception {
        return nutritionistRepository.findByFirstNameAndLastName(firstname, lastname);
    }
}
