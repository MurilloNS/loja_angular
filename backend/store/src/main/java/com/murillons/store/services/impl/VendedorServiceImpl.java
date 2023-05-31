package com.murillons.store.services.impl;

import com.murillons.store.components.NullAwareBeanUtilsBean;
import com.murillons.store.dto.VendedorRequest;
import com.murillons.store.dto.VendedorUpdate;
import com.murillons.store.entities.Vendedor;
import com.murillons.store.repositories.VendedorRepository;
import com.murillons.store.services.VendedorService;
import com.murillons.store.services.exceptions.DataAlreadyExistException;
import com.murillons.store.services.exceptions.UserNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

@Service
public class VendedorServiceImpl implements VendedorService {
    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private NullAwareBeanUtilsBean nullAwareBeanUtilsBean;

    @Override
    public Vendedor saveVendedor(VendedorRequest vendedorRequest) {
        boolean vendedorEmail = vendedorRepository.findVendedorByEmail(vendedorRequest.getEmail()) != null;
        boolean vendedorCpf = vendedorRepository.findVendedorByCpf(vendedorRequest.getCpf()) != null;
        boolean vendedorCnpj = vendedorRepository.findVendedorByCnpj(vendedorRequest.getCnpj()) != null;

        if (vendedorEmail) {
            throw new DataAlreadyExistException("Esse e-mail já está cadastrado");
        } else if (vendedorCpf) {
            throw new DataAlreadyExistException("Esse CPF já está cadastrado");
        } else if (vendedorCnpj) {
            throw new DataAlreadyExistException("Esse CNPJ já está cadastrado");
        }

        Vendedor vendedor = Vendedor.builder()
                .name(vendedorRequest.getName())
                .email(vendedorRequest.getEmail())
                .password(vendedorRequest.getPassword())
                .cpf(vendedorRequest.getCpf())
                .cnpj(vendedorRequest.getCnpj())
                .created(LocalDateTime.now())
                .enabled(false).build();

        return vendedorRepository.save(vendedor);
    }

    @Override
    public VendedorUpdate updateVendedor(Long idVendedor, VendedorUpdate vendedorUpdate) throws InvocationTargetException, IllegalAccessException {
        Vendedor vendedor = vendedorRepository.findById(idVendedor)
                .orElseThrow(() -> new UserNotExistException("Vendedor não encontrado!"));
        nullAwareBeanUtilsBean.copyProperties(vendedor, vendedorUpdate);
        vendedorRepository.save(vendedor);
        return vendedorUpdate;
    }

    @Override
    public void deleteVendedor(Long idVendedor) {
        vendedorRepository.deleteById(idVendedor);
    }
}
