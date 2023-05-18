package com.murillons.store.services;

import com.murillons.store.dto.VendedorRequest;
import com.murillons.store.entities.Vendedor;

public interface VendedorService {
    Vendedor saveVendedor(VendedorRequest vendedorRequest);
}
