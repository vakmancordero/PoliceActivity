package com.bsdenterprise.qbits.policeactivity.controller;

import com.bsdenterprise.qbits.policeactivity.dto.environment.EnvironmentInDTO;
import com.bsdenterprise.qbits.policeactivity.dto.environment.EnvironmentOutDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.bsdenterprise.qbits.policeactivity.common.exceptions.EntityNotFoundException;
import com.bsdenterprise.qbits.policeactivity.common.exceptions.ValidationException;
import com.bsdenterprise.qbits.policeactivity.service.EnvironmentService;

import javax.validation.Valid;
import java.util.List;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/catalogs/environments")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "environment", description = "Endpoint for environment management")
public class EnvironmentController {

    private final EnvironmentService environmentService;

    @GetMapping
    public @ResponseBody ResponseEntity<List<EnvironmentOutDTO>> findAll() {
        return ResponseEntity.ok(this.environmentService.findAll(EnvironmentOutDTO.class));
    }

    @GetMapping("/find")
    public @ResponseBody ResponseEntity<List<EnvironmentOutDTO>> findByNameContaining(@RequestParam("query") String query) {
        return ResponseEntity.ok(this.environmentService.findByQueryContaining(query));
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity findById(@PathVariable("id") long id) {

        try {
            return ResponseEntity.ok(this.environmentService.findById(id, EnvironmentOutDTO.class));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Environment not found");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error getting the environment, " + ex.getMessage());
        }
    }

    @PostMapping
    public @ResponseBody ResponseEntity create(@Valid @RequestBody EnvironmentInDTO environmentInDTO, BindingResult result) {

        if (result.hasErrors())
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);

        try {
            return new ResponseEntity<>(this.environmentService.createEnvironment(environmentInDTO), HttpStatus.OK);
        } catch (ValidationException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error creating the environment, " + ex.getMessage());
        }

    }

    @PutMapping("/{id}")
    public @ResponseBody ResponseEntity update(
            @PathVariable("id") long id, @Valid @RequestBody EnvironmentInDTO environmentInDTO, BindingResult result) {

        if (result.hasErrors())
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);

        try {
            return new ResponseEntity<>(this.environmentService.updateEnvironment(id, environmentInDTO), HttpStatus.OK);
        } catch (ValidationException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error updating the environment, " + ex.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity delete(@PathVariable("id") long id) {

        try {
            return ResponseEntity.ok(this.environmentService.delete(id, EnvironmentOutDTO.class));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Environment not found");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error deleting the environment, " + ex.getMessage());
        }

    }

}
