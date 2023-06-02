package com.murillons.store.controllers;

import com.murillons.store.entities.Paper;
import com.murillons.store.services.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/papers")
public class PaperController {
    @Autowired
    private PaperService paperService;

    @PostMapping("/save")
    public ResponseEntity<Paper> savePaper(@RequestBody Paper paper) {
        Paper newPaper = paperService.savePaper(paper);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(paper.getId())
                .toUri();

        return ResponseEntity.created(location).body(newPaper);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Paper>> listPapers() {
        return ResponseEntity.ok(paperService.listPapers());
    }

    @PutMapping("/edit/{idPaper}")
        public ResponseEntity<Paper> updatePaper(@PathVariable Long idPaper, @RequestBody Paper paper) {
            return ResponseEntity.ok(paperService.updatePaper(idPaper, paper));
    }

    @DeleteMapping("/delete/{idPaper}")
    public ResponseEntity<Void> deletePaper(@PathVariable Long idPaper) {
        paperService.deletePaper(idPaper);
        return ResponseEntity.noContent().build();
    }
}