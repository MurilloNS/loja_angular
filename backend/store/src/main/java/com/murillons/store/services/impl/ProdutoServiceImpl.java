package com.murillons.store.services.impl;

import com.murillons.store.components.NullAwareBeanUtilsBean;
import com.murillons.store.dto.ProdutoUpdate;
import com.murillons.store.entities.Produto;
import com.murillons.store.repositories.ProdutoRepository;
import com.murillons.store.repositories.VendedorRepository;
import com.murillons.store.services.ProdutoService;
import com.murillons.store.services.exceptions.UserNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private NullAwareBeanUtilsBean nullAwareBeanUtilsBean;

    @Override
    public Produto saveProduto(Long idVendedor, Produto produto) {
        produto.setVendedor(vendedorRepository.findById(idVendedor).get());
        return produtoRepository.save(produto);
    }

    @Override
    public List<Produto> listProdutos(Long idVendedor) {
        return produtoRepository.findProdutosByIdVendedor(idVendedor);
    }

    @Override
    public ProdutoUpdate updateProduto(Long idProduto, ProdutoUpdate produtoUpdate) throws InvocationTargetException, IllegalAccessException {
        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new UserNotExistException("Produto não encontrado!"));
        nullAwareBeanUtilsBean.copyProperties(produto, produtoUpdate);
        produtoRepository.save(produto);
        return produtoUpdate;
    }

    @Override
    public void deleteProduto(Long idProduto) {
        produtoRepository.deleteById(idProduto);
    }
}
