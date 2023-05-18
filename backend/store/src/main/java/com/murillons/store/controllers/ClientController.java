package com.murillons.store.controllers;

import com.murillons.store.dto.ClientRequest;
import com.murillons.store.dto.ClientUpdate;
import com.murillons.store.entities.Client;
import com.murillons.store.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("/save")
    public ResponseEntity<Client> saveClient(@Valid @RequestBody ClientRequest clientRequest) {
        Client client = clientService.saveClient(clientRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(client.getId())
                .toUri();

        return ResponseEntity.created(location).body(client);
    }

    @PatchMapping("/edit/{idClient}")
    public ResponseEntity<ClientUpdate> updateClient(@PathVariable Long idClient, @Valid @RequestBody ClientUpdate clientUpdate) throws InvocationTargetException, IllegalAccessException {
        return ResponseEntity.ok(clientService.updateClient(idClient, clientUpdate));
    }

    @DeleteMapping("delete/{idClient}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long idClient) {
        clientService.deleteClient(idClient);
        return ResponseEntity.noContent().build();
    }
}
