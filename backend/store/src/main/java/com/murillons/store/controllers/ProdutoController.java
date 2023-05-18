package com.murillons.store.controllers;

import com.murillons.store.dto.ProdutoUpdate;
import com.murillons.store.entities.Produto;
import com.murillons.store.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.lang.reflect.InvocationTargetException;
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

    @PatchMapping("/edit/{idProduto}")
    public ResponseEntity<ProdutoUpdate> updateProduto(@PathVariable Long idProduto, @RequestBody ProdutoUpdate produtoUpdate) throws InvocationTargetException, IllegalAccessException {
        return ResponseEntity.ok(produtoService.updateProduto(idProduto, produtoUpdate));
    }

    @DeleteMapping("/delete/{idProduto}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long idProduto) {
        produtoService.deleteProduto(idProduto);
        return ResponseEntity.noContent().build();
    }
}
