package com.nutrix.query.api;

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

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/nutritionist")
@Api(tags="Nutritionist", value="Servicio Web RESTFul de Nutritionist")
public class NutritionistQueryController {

    @Autowired
    private NutritionistQueryService nutritionistService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Listar Nutritionists", notes = "Método para listar todos los Nutritionists")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Nutritionists encontrados"),
            @ApiResponse(code = 404, message = "Nutritionists no encontrados")
    })
    public ResponseEntity<List<Nutritionist>> findAll() {
        try {
            List<Nutritionist> nutritionists = nutritionistService.getAll();
            if (nutritionists.size() > 0)
                return new ResponseEntity<List<Nutritionist>>(nutritionists, HttpStatus.OK);
            else
                return new ResponseEntity<List<Nutritionist>>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<List<Nutritionist>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Nutritionist por Id", notes = "Método para encontrar un Nutritionist por su respectivo Id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Nutritionist encontrado"),
            @ApiResponse(code = 404, message = "Nutritionist no encontrado")
    })
    public ResponseEntity<Nutritionist> findById(@PathVariable("id") Integer id) {
        try {
            Optional<Nutritionist> nutritionist = nutritionistService.getById(id);
            if (!nutritionist.isPresent())
                return new ResponseEntity<Nutritionist>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Nutritionist>(nutritionist.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Nutritionist>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchByUsername/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Nutritionist por Username", notes = "Método para encontrar un Nutritionist por su respectivo Username")
    @ApiResponses({
            @ApiResponse(code=201, message = "Nutritionist encontrado"),
            @ApiResponse(code=404, message = "Nutritionist no encontrado")
    })
    public ResponseEntity<Nutritionist> findByUsername(@PathVariable("username") String username){
        try{
            Nutritionist nutritionist = nutritionistService.findByUsername(username);
            if(nutritionist==null)
                return new ResponseEntity<Nutritionist>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Nutritionist>(nutritionist,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<Nutritionist>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchByCnpNumber/{cnp_number}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Nutritionist por su CNP", notes = "Método para encontrar un Nutritionist por su respectivo CNP")
    @ApiResponses({
            @ApiResponse(code=201, message = "Nutritionist encontrado"),
            @ApiResponse(code=404, message = "Nutritionist no encontrado")
    })
    public ResponseEntity<Nutritionist> findByCnpNumber(@PathVariable("cnp_number") Integer cnp_number){
        try{
            Nutritionist nutritionist = nutritionistService.findByCnpNumber(cnp_number);
            if(nutritionist==null)
                return new ResponseEntity<Nutritionist>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Nutritionist>(nutritionist, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<Nutritionist>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="searchByFirstname/{firstname}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Nutritionist por firstname", notes = "Método para encontrar Nutritionists por su respectivo firstname")
    @ApiResponses({
            @ApiResponse(code=201, message = "Nutritionists encontrados"),
            @ApiResponse(code=404, message = "Nutritionists no encontrados")
    })
    public ResponseEntity<List<Nutritionist>> findByFirstName(@PathVariable("firstname") String firstname){
        try{
            List<Nutritionist> nutritionists = nutritionistService.findByFirstName(firstname);
            if(nutritionists.size()>0)
                return new ResponseEntity<List<Nutritionist>>(nutritionists, HttpStatus.OK);
            else
                return new ResponseEntity<List<Nutritionist>>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<List<Nutritionist>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="searchByLastname/{lastname}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Nutritionist por lastname", notes = "Método para encontrar Nutritionists por su respectivo lastname")
    @ApiResponses({
            @ApiResponse(code=201, message = "Nutritionists encontrados"),
            @ApiResponse(code=404, message = "Nutritionists no encontrados")
    })
    public ResponseEntity<List<Nutritionist>> findByLastName(@PathVariable("lastname") String lastname){
        try{
            List<Nutritionist> nutritionists = nutritionistService.findByLastName(lastname);
            if(nutritionists.size()>0)
                return new ResponseEntity<List<Nutritionist>>(nutritionists, HttpStatus.OK);
            else
                return new ResponseEntity<List<Nutritionist>>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<List<Nutritionist>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="searchByFirstnameAndLastname/{firstname}/{lastname}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Nutritionists por firstname y lastname", notes = "Método para encontrar Nutritionists por su respectivo firstname y lastname")
    @ApiResponses({
            @ApiResponse(code=201, message = "Nutritionists encontrados"),
            @ApiResponse(code=404, message = "Nutritionists no encontrados")
    })
    public ResponseEntity<List<Nutritionist>> findByFirstnameAndLastname(
            @PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname){
        try{
            List<Nutritionist> nutritionists = nutritionistService.findByFirstNameAndLastName(firstname,lastname);
            if(nutritionists.size()>0)
                return new ResponseEntity<List<Nutritionist>>(nutritionists, HttpStatus.OK);
            else
                return new ResponseEntity<List<Nutritionist>>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<List<Nutritionist>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}