package com.murillons.store.controllers;

import com.murillons.store.dto.VendedorRequest;
import com.murillons.store.entities.Vendedor;
import com.murillons.store.services.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/vendedores")
public class VendedorController {
    @Autowired
    private VendedorService vendedorService;

    @PostMapping("/save")
    public ResponseEntity<Vendedor> saveVendedor(@Valid @RequestBody VendedorRequest vendedorRequest) {
        Vendedor vendedor = vendedorService.saveVendedor(vendedorRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(vendedor.getId())
                .toUri();

        return ResponseEntity.created(location).body(vendedor);
    }
}
