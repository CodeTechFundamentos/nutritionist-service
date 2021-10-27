package com.nutrix.query.api;

import com.nutrix.command.domain.ProfessionalProfile;
import com.nutrix.command.domain.Specialty;
import com.nutrix.command.infra.IProfessionalSpecialtiesRepository;
import com.nutrix.query.application.services.ProfessionalProfileQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professional-profile")
@Api(tags = "Profesional Profile", value = "Servicio Web RESTful de Profesional_profiles")
public class ProfessionalProfileQueryController {

    @Autowired
    private ProfessionalProfileQueryService professionalProfileService;
    @Autowired
    private IProfessionalSpecialtiesRepository professionalSpecialtiesRepository;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Profesional Profiles", notes = "Método para encontrar Profesional Profiles ")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Profesional Profiles encontrados"),
            @ApiResponse(code = 404, message = "Profesional Profiles no encontrados")
    })
    public ResponseEntity<List<ProfessionalProfile>>findAll(){
        try {
            List<ProfessionalProfile> professionalProfiles = new ArrayList<>();
            professionalProfiles = professionalProfileService.getAll();
            return new ResponseEntity<List<ProfessionalProfile>>(professionalProfiles, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<List<ProfessionalProfile>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Profesional Profile por Id", notes = "Método para encontrar Profesional Profile por Id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Profesional Profile encontrado"),
            @ApiResponse(code = 404, message = "Profesional Profile no encontrado")
    })
    public ResponseEntity<ProfessionalProfile> findById(@PathVariable("id") Integer id){
        try {
            Optional<ProfessionalProfile> professionalProfile = professionalProfileService.getById(id);
            if(!professionalProfile.isPresent())
                return new ResponseEntity<ProfessionalProfile>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<ProfessionalProfile>(professionalProfile.get(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<ProfessionalProfile>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @GetMapping(value = "/findSpecialtiesByProfessionalProfileId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Specialties de un ProfessionalProfile", notes = "Método para listar Specialties de un ProfessionalProfile")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Specialties encontrados"),
            @ApiResponse(code = 404, message = "Specialties no encontrados")
    })
    public ResponseEntity<List<Specialty>> findSpecialtiesByProfessionalProfileId(@PathVariable("id") Integer id)
    {
        try {
            List<Specialty> specialties = new ArrayList<>();
            specialties = professionalSpecialtiesRepository.findByProfessionalProfileId(id);
            if(specialties.isEmpty())
                return new ResponseEntity<List<Specialty>>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<List<Specialty>>(specialties, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<List<Specialty>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
