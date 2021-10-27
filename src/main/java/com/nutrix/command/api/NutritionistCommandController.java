package com.nutrix.command.api;

import com.nutrix.command.application.services.NutritionistCommandService;
import com.nutrix.command.domain.Nutritionist;
import com.nutrix.query.application.services.NutritionistQueryService;
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
@RequestMapping("/nutritionist")
@Api(tags="Nutritionist", value="Servicio Web RESTFul de Nutritionist")
public class NutritionistCommandController {

    @Autowired
    private NutritionistCommandService nutritionistService;
    @Autowired
    private NutritionistQueryService nutritionistQueryService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registro de Nutritionists", notes = "Método que registra customers en BD")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Nutritionist creado"),
            @ApiResponse(code = 404, message = "Nutritionist no creado")
    })
    public ResponseEntity<Nutritionist> insertNutritionist(@Valid @RequestBody Nutritionist nutritionist) {
        try {
            Nutritionist nutritionistNew = nutritionistService.save(nutritionist);
            return ResponseEntity.status(HttpStatus.CREATED).body(nutritionistNew);
        } catch (Exception e) {
            return new ResponseEntity<Nutritionist>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Actualización de datos de Nutritionists", notes = "Método que actualiza los datos de Nutritionists")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Datos de Nutritionist actualizado"),
            @ApiResponse(code = 404, message = "Nutritionist no encontrado")
    })
    public ResponseEntity<Nutritionist> updateNutritionist(
            @PathVariable("id") Integer id, @Valid @RequestBody Nutritionist nutritionist) {
        try {
            Optional<Nutritionist> nutritionistUp = nutritionistQueryService.getById(id);
            if (!nutritionistUp.isPresent())
                return new ResponseEntity<Nutritionist>(HttpStatus.NOT_FOUND);
            nutritionist.setId(id);
            nutritionistService.save(nutritionist);
            return new ResponseEntity<Nutritionist>(nutritionist, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Nutritionist>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Eliminación de datos de Nutritionist", notes = "Método que elimina los datos de Nutritionist en BD")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Datos de Nutritionist eliminados"),
            @ApiResponse(code = 404, message = "Nutritionist no encontrado")
    })
    public ResponseEntity<Nutritionist> deleteNutritionist(@PathVariable("id") Integer id) {
        try {
            Optional<Nutritionist> nutritionistDelete = nutritionistQueryService.getById(id);
            if (!nutritionistDelete.isPresent())
                return new ResponseEntity<Nutritionist>(HttpStatus.NOT_FOUND);
            nutritionistService.delete(id);
            return new ResponseEntity<Nutritionist>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Nutritionist>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}