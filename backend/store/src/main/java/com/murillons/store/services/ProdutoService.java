package com.murillons.store.services;

import com.murillons.store.entities.Produto;

public interface ProdutoService {
    Produto saveProduto(Long idVendedor, Produto produto);
}
