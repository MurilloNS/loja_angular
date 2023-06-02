package com.murillons.store.controllers;

import com.murillons.store.entities.Paper;
import com.murillons.store.services.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/papers")
public class PaperController {
    @Autowired
    private PaperService paperService;

    @PostMapping("/save/{idAdmin}")
    public ResponseEntity<Paper> savePaper(@RequestBody Paper paper, @PathVariable Long idAdmin) {
        Paper newPaper = paperService.savePaper(paper, idAdmin);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(paper.getId())
                .toUri();

        return ResponseEntity.created(location).body(newPaper);
    }
}