package com.nutrix.command.api;

import com.nutrix.command.application.services.ProfessionalProfileCommandService;
import com.nutrix.command.domain.ProfessionalProfile;
import com.nutrix.command.domain.ProfessionalSpecialties;
import com.nutrix.command.domain.ProfessionalSpecialtiesFK;
import com.nutrix.command.domain.Specialty;
import com.nutrix.command.infra.IProfessionalSpecialtiesRepository;
import com.nutrix.query.application.services.ProfessionalProfileQueryService;
import com.nutrix.query.application.services.SpecialtyQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/professional-profile")
@Api(tags = "Profesional Profile", value = "Servicio Web RESTful de Profesional_profiles")
public class ProfessionalProfileCommandController {

    @Autowired
    private ProfessionalProfileCommandService professionalProfileService;
    @Autowired
    private ProfessionalProfileQueryService professionalProfileQueryService;
    @Autowired
    private SpecialtyQueryService specialtyService;
    @Autowired
    private IProfessionalSpecialtiesRepository professionalSpecialtiesRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registro de Profesional Profile", notes = "Método para agregar un Profesional Profile")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Profesional Profile agregado"),
            @ApiResponse(code = 404, message = "Profesional Profile no fue agregado")
    })
    public ResponseEntity<ProfessionalProfile> insertProfessionalProfile(@Valid @RequestBody ProfessionalProfile professionalProfile){
        try {
            ProfessionalProfile professionalProfileNew = professionalProfileService.save(professionalProfile);
            return ResponseEntity.status(HttpStatus.CREATED).body(professionalProfileNew);
        }catch (Exception e){
            return new ResponseEntity<ProfessionalProfile>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Actualizar un Profesional Profile", notes = "Método para actualizar un Profesional Profile")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Profesional Profile actualizado"),
            @ApiResponse(code = 404, message = "Profesional Profile no encontrado")
    })
    public ResponseEntity<ProfessionalProfile> updateProfessionalProfile(@PathVariable("id") Integer id, @Valid @RequestBody ProfessionalProfile professionalProfile){
        try {
            Optional<ProfessionalProfile> professionalProfileOptional = professionalProfileQueryService.getById(id);
            if(!professionalProfileOptional.isPresent())
                return new ResponseEntity<ProfessionalProfile>(HttpStatus.NOT_FOUND);
            professionalProfile.setId(id);
            professionalProfileService.save(professionalProfile);
            return new ResponseEntity<ProfessionalProfile>(professionalProfile,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<ProfessionalProfile>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Eliminación de un Profesional Profile", notes = "Método para eliminar un Profesional Profile")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Profesional Profile eliminado"),
            @ApiResponse(code = 404, message = "Profesional Profile no encontrado")
    })
    public ResponseEntity<ProfessionalProfile> deleteDiet(@PathVariable("id") Integer id){
        try {
            Optional<ProfessionalProfile> professionalProfileOptional = professionalProfileQueryService.getById(id);
            if(!professionalProfileOptional.isPresent())
                return new ResponseEntity<ProfessionalProfile>(HttpStatus.NOT_FOUND);
            professionalProfileService.delete(id);
            return new ResponseEntity<ProfessionalProfile>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<ProfessionalProfile>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/{specialty_id}/{professional_profile_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Adición de Specialty a un ProfessionalProfile", notes = "Método que añade un Specialty a un ProfessionalProfile")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Specialty añadida al ProfessionalProfile"),
            @ApiResponse(code = 404, message = "Specialty o ProfessionalProfile no encontrado")
    })
    public ResponseEntity<ProfessionalSpecialties> addSpecialtyToProfessionalProfile(@PathVariable("specialty_id") Integer specialty_id,
                                                                                     @PathVariable("professional_profile_id") Integer professional_profile_id){
        try {
            Optional<Specialty> foundSpecialty = specialtyService.getById(specialty_id);
            Optional<ProfessionalProfile> foundProfessionalProfile = professionalProfileQueryService.getById(professional_profile_id);
            if(!foundSpecialty.isPresent())
                return new ResponseEntity<ProfessionalSpecialties>(HttpStatus.NOT_FOUND);
            if(!foundProfessionalProfile.isPresent())
                return new ResponseEntity<ProfessionalSpecialties>(HttpStatus.NOT_FOUND);
            ProfessionalSpecialtiesFK newFKS = new ProfessionalSpecialtiesFK(professional_profile_id, specialty_id);
            ProfessionalSpecialties professionalSpecialties = new ProfessionalSpecialties(newFKS, foundProfessionalProfile.get(), foundSpecialty.get());

            professionalSpecialtiesRepository.save(professionalSpecialties);
            return ResponseEntity.status(HttpStatus.CREATED).body(professionalSpecialties);
        }catch (Exception e){
            return new ResponseEntity<ProfessionalSpecialties>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{specialty_id}/{professional_profile_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Eliminación de un Specialty de un ProfessionalProfile", notes = "Método para eliminar un Specialty de un ProfessionalProfile")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Specialty eliminado"),
            @ApiResponse(code = 404, message = "Specialty no encontrado")
    })
    public ResponseEntity<ProfessionalProfile> deleteSpecialtyFromProfessionalProfile(@PathVariable("specialty_id") Integer specialty_id,
                                                                                      @PathVariable("professional_profile_id") Integer professional_profile_id)
    {
        try{
            ProfessionalSpecialtiesFK newFKS = new ProfessionalSpecialtiesFK(professional_profile_id, specialty_id);
            Optional<ProfessionalSpecialties> professionalSpecialties = professionalSpecialtiesRepository.findById(newFKS);
            if(!professionalSpecialties.isPresent())
                return new ResponseEntity<ProfessionalProfile>(HttpStatus.NOT_FOUND);
            professionalSpecialtiesRepository.delete(professionalSpecialties.get());
            return new ResponseEntity<ProfessionalProfile>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<ProfessionalProfile>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
