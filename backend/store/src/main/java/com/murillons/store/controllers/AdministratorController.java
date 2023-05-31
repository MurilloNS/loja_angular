package com.murillons.store.controllers;

import com.murillons.store.dto.AdministratorRequest;
import com.murillons.store.dto.AdministratorUpdate;
import com.murillons.store.entities.Administrator;
import com.murillons.store.services.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;

@RestController
@RequestMapping("/admin")
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;

    @PostMapping("/save")
    public ResponseEntity<Administrator> saveAdm(@Valid @RequestBody AdministratorRequest administratorRequest) {
        Administrator administrator = administratorService.saveAdministrator(administratorRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(administrator.getId())
                .toUri();

        return ResponseEntity.created(location).body(administrator);
    }

    @PatchMapping("/edit/{idAdmin}")
    public ResponseEntity<AdministratorUpdate> updateAdmin(@PathVariable Long idAdmin, @Valid @RequestBody AdministratorUpdate administratorUpdate) throws InvocationTargetException, IllegalAccessException {
        return ResponseEntity.ok(administratorService.updateAdministrator(idAdmin, administratorUpdate));
    }

    @DeleteMapping("delete/{idAdmin}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long idAdmin) {
        administratorService.deleteAdministrator(idAdmin);
        return ResponseEntity.noContent().build();
    }
}