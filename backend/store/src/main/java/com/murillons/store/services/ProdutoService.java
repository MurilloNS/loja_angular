package com.murillons.store.services;

import com.murillons.store.dto.ProdutoUpdate;
import com.murillons.store.entities.Produto;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface ProdutoService {
    Produto saveProduto(Long idVendedor, Produto produto);

    List<Produto> listProdutos(Long idVendedor);

    ProdutoUpdate updateProduto(Long idProduto, ProdutoUpdate produtoUpdate) throws InvocationTargetException, IllegalAccessException;

    void deleteProduto(Long idProduto);
}
