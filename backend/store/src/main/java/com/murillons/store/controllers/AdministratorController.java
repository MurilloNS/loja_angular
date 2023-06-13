package com.murillons.store.controllers;

import com.murillons.store.dto.AdministratorRequest;
import com.murillons.store.dto.AdministratorUpdate;
import com.murillons.store.entities.Administrator;
import com.murillons.store.security.JwtUtil;
import com.murillons.store.services.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.HashMap;

@RestController
@RequestMapping("/admin")
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Administrator administrator) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(administrator.getEmail(), administrator.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Administrator result = (Administrator) authentication.getPrincipal();
        String token = jwtUtil.tokenAdministratorGenerate(result);
        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("email", administrator.getEmail());
        return ResponseEntity.ok(map);
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