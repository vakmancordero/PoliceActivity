package com.bsdenterprise.qbits.policeactivity.controller;

import com.bsdenterprise.qbits.policeactivity.dto.status.StatusDTO;
import com.bsdenterprise.qbits.policeactivity.service.StatusService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/catalogs/status")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "status", description = "Endpoint for status management")
public class StatusController {

    private final StatusService statusService;

    @GetMapping
    public @ResponseBody ResponseEntity<List<StatusDTO>> findAll() {
        return ResponseEntity.ok(statusService.findAll());
    }

    @GetMapping("/find")
    public @ResponseBody ResponseEntity<List<StatusDTO>> findByNameContaining(@RequestParam("query") String query) {
        return ResponseEntity.ok(statusService.findByQueryContaining(query));
    }

}
