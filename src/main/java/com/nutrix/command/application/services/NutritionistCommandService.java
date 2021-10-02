package com.nutrix.command.application.services;

import com.nutrix.command.domain.Nutritionist;
import com.nutrix.command.infra.INutritionistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class NutritionistCommandService extends Nutritionist {
    @Autowired
    private INutritionistRepository nutritionistRepository;

    @Override
    @Transactional
    public Nutritionist save(Nutritionist nutritionist) throws Exception {
        return nutritionistRepository.save(nutritionist);
    }

    @Override
    @Transactional
    public void delete(Integer id) throws Exception {
        nutritionistRepository.deleteById(id);
    }
}
