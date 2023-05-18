package com.murillons.store.repositories;

import com.murillons.store.entities.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
    Vendedor findVendedorByEmail(String email);
    Vendedor findVendedorByCpf(String cpf);
    Vendedor findVendedorByCnpj(String cnpj);
}
