package com.murillons.store.repositories;

import com.murillons.store.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    @Query("SELECT p FROM Produto p WHERE p.vendedor.id = :idVendedor")
    List<Produto> findProdutosByIdVendedor(@Param("idVendedor") Long idVendedor);
}
