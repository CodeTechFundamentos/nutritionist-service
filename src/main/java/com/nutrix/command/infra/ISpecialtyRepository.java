package com.nutrix.command.infra;


import com.nutrix.command.domain.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISpecialtyRepository extends JpaRepository<Specialty, Integer> {
    public List<Specialty> findByInstitutionName(String institution_name);

    @Query("Select b from Specialty b where b.name = :name and b.institutionName = :institution_name")
    public Specialty findByNameAndInstitutionName(String name, String institution_name);
}