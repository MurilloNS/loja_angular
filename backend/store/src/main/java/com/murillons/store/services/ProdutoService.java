package com.murillons.store.services;

import com.murillons.store.entities.Produto;

import java.util.List;

public interface ProdutoService {
    Produto saveProduto(Long idVendedor, Produto produto);

    List<Produto> listProdutos(Long idVendedor);
}
