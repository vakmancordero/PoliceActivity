package com.bsdenterprise.qbits.policeactivity.controller;

import com.bsdenterprise.qbits.policeactivity.common.exceptions.EntityNotFoundException;
import com.bsdenterprise.qbits.policeactivity.common.exceptions.ValidationException;
import com.bsdenterprise.qbits.policeactivity.dto.module.ModuleInDTO;
import com.bsdenterprise.qbits.policeactivity.dto.module.ModuleOutDTO;
import com.bsdenterprise.qbits.policeactivity.service.ModuleService;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/catalogs/modules")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "module", description = "Endpoint for module management")
public class ModuleController {

    private final ModuleService moduleService;

    @GetMapping
    public @ResponseBody ResponseEntity<List<ModuleOutDTO>> findAll() {
        return ResponseEntity.ok(this.moduleService.findAll(ModuleOutDTO.class));
    }

    @GetMapping("/find")
    public @ResponseBody ResponseEntity<List<ModuleOutDTO>> findByNameContaining(@RequestParam("query") String query) {
        return ResponseEntity.ok(this.moduleService.findByQueryContaining(query));
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity findById(@PathVariable("id") long id) {

        try {
            return ResponseEntity.ok(this.moduleService.findById(id, ModuleOutDTO.class));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Module not found");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error getting the module, " + ex.getMessage());
        }
    }

    @PostMapping
    public @ResponseBody ResponseEntity create(@Valid @RequestBody ModuleInDTO moduleInDTO, BindingResult result) {

        if (result.hasErrors())
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);

        try {
            return new ResponseEntity<>(this.moduleService.createModule(moduleInDTO), HttpStatus.OK);
        } catch (ValidationException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error creating the module, " + ex.getMessage());
        }

    }

    @PutMapping("/{id}")
    public @ResponseBody ResponseEntity update(
            @PathVariable("id") long id, @Valid @RequestBody ModuleInDTO moduleInDTO, BindingResult result) {

        if (result.hasErrors())
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);

        try {
            return new ResponseEntity<>(this.moduleService.updateModule(id, moduleInDTO), HttpStatus.OK);
        } catch (ValidationException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error updating the module, " + ex.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity delete(@PathVariable("id") long id) {

        try {
            return ResponseEntity.ok(this.moduleService.delete(id, ModuleOutDTO.class));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Module not found");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error deleting the module, " + ex.getMessage());
        }

    }

}
