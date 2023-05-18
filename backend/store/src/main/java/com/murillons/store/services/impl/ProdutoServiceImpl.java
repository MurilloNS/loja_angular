package com.murillons.store.services.impl;

import com.murillons.store.entities.Produto;
import com.murillons.store.repositories.ProdutoRepository;
import com.murillons.store.repositories.VendedorRepository;
import com.murillons.store.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private VendedorRepository vendedorRepository;

    @Override
    public Produto saveProduto(Long idVendedor, Produto produto) {
        produto.setVendedor(vendedorRepository.findById(idVendedor).get());
        return produtoRepository.save(produto);
    }

    @Override
    public List<Produto> listProdutos(Long idVendedor) {
        return produtoRepository.findProdutosByIdVendedor(idVendedor);
    }
}
