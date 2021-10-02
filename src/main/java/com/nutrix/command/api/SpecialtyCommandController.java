package com.nutrix.command.api;

import com.nutrix.command.application.services.SpecialtyCommandService;
import com.nutrix.command.domain.Specialty;
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
@RequestMapping("/specialty")
@Api(tags = "Specialty", value = "Servicio Web RESTful de Specialtys")
public class SpecialtyCommandController {

    @Autowired
    private SpecialtyCommandService specialtyService;
    @Autowired
    private SpecialtyQueryService specialtyQueryService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registro de Specialty", notes = "Método para agregar un Specialty")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Specialty agregado"),
            @ApiResponse(code = 404, message = "Specialty no fue agregado")
    })
    public ResponseEntity<Specialty> insertSpecialty(@Valid @RequestBody Specialty specialty){
        try {
            Specialty specialtyNew = specialtyService.save(specialty);
            return ResponseEntity.status(HttpStatus.CREATED).body(specialtyNew);
        }catch (Exception e){
            return new ResponseEntity<Specialty>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Actualización de un Specialty", notes = "Método para actualizar un Specialty")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Specialty actualizado"),
            @ApiResponse(code = 404, message = "Specialty no encontrado")
    })
    public ResponseEntity<Specialty> updateSpecialty(@PathVariable("id") Integer id, @Valid @RequestBody Specialty specialty){
        try {
            Optional<Specialty> specialtyOptional = specialtyQueryService.getById(id);
            if(!specialtyOptional.isPresent())
                return new ResponseEntity<Specialty>(HttpStatus.NOT_FOUND);
            specialty.setId(id);
            specialtyService.save(specialty);
            return new ResponseEntity<Specialty>(specialty, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Specialty>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @DeleteMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Eliminación de un Specialty", notes = "Método para eliminar un Specialty")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Specialty eliminado"),
            @ApiResponse(code = 404, message = "Specialty no encontrado")
    })
    public ResponseEntity<Specialty> deleteSpecialty(@PathVariable("id") Integer id){
        try {
            Optional<Specialty> specialtyOptional = specialtyQueryService.getById(id);
            if(!specialtyOptional.isPresent())
                return new ResponseEntity<Specialty>(HttpStatus.NOT_FOUND);
            specialtyService.delete(id);
            return new ResponseEntity<Specialty>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Specialty>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
