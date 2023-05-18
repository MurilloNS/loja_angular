package com.murillons.store.controllers;

import com.murillons.store.entities.Produto;
import com.murillons.store.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;
    @PostMapping("/save/{idVendedor}")
    public ResponseEntity<Produto> saveProduto(@PathVariable Long idVendedor, @RequestBody Produto produto) {
        Produto newProduto = produtoService.saveProduto(idVendedor, produto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(newProduto.getId())
                .toUri();

        return ResponseEntity.created(location).body(newProduto);
    }

    @GetMapping("/list/{idVendedor}")
    public ResponseEntity<List<Produto>> listProdutos(@PathVariable Long idVendedor) {
        return ResponseEntity.ok(produtoService.listProdutos(idVendedor));
    }
}
