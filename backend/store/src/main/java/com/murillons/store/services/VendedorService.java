package com.murillons.store.services;

import com.murillons.store.dto.VendedorRequest;
import com.murillons.store.dto.VendedorUpdate;
import com.murillons.store.entities.Vendedor;

import java.lang.reflect.InvocationTargetException;

public interface VendedorService {
    Vendedor saveVendedor(VendedorRequest vendedorRequest);

    VendedorUpdate updateVendedor(Long idVendedor, VendedorUpdate vendedorUpdate) throws InvocationTargetException, IllegalAccessException;
}
