package com.murillons.store.controllers;

import com.murillons.store.dto.VendedorRequest;
import com.murillons.store.dto.VendedorUpdate;
import com.murillons.store.entities.Vendedor;
import com.murillons.store.services.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
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

    @PatchMapping("/edit/{idVendedor}")
    public ResponseEntity<VendedorUpdate> updateVendedor(@PathVariable Long idVendedor, @Valid @RequestBody VendedorUpdate vendedorUpdate) throws InvocationTargetException, IllegalAccessException {
        return ResponseEntity.ok(vendedorService.updateVendedor(idVendedor, vendedorUpdate));
    }
}
