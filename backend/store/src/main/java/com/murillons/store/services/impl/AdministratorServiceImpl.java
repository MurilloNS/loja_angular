package com.murillons.store.services.impl;

import com.murillons.store.components.NullAwareBeanUtilsBean;
import com.murillons.store.dto.AdministratorRequest;
import com.murillons.store.dto.AdministratorUpdate;
import com.murillons.store.entities.Administrator;
import com.murillons.store.repositories.AdministratorRepository;
import com.murillons.store.services.AdministratorService;
import com.murillons.store.services.exceptions.DataAlreadyExistException;
import com.murillons.store.services.exceptions.UserNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

@Service
public class AdministratorServiceImpl implements AdministratorService {
    @Autowired
    private AdministratorRepository administratorRepository;

    @Autowired
    private NullAwareBeanUtilsBean nullAwareBeanUtilsBean;

    @Override
    public Administrator saveAdministrator(AdministratorRequest administratorRequest) {
        boolean emailVerified = administratorRepository.findByEmail(administratorRequest.getEmail()) != null;
        boolean cpfVerified = administratorRepository.findByCpf(administratorRequest.getCpf()) != null;

        if (emailVerified)
            throw new DataAlreadyExistException("Esse e-mail já está cadastrado.");
        else if (cpfVerified)
            throw new DataAlreadyExistException("Esse CPF já está cadastrado.");

        Administrator administrator = Administrator.builder()
                .name(administratorRequest.getName())
                .email(administratorRequest.getEmail())
                .password(administratorRequest.getPassword())
                .cpf(administratorRequest.getCpf())
                .created(LocalDateTime.now())
                .build();

        return administratorRepository.save(administrator);
    }

    @Override
    public AdministratorUpdate updateAdministrator(Long idAdmin, AdministratorUpdate administratorUpdate) throws InvocationTargetException, IllegalAccessException {
        Administrator administrator = administratorRepository.findById(idAdmin)
                .orElseThrow(() -> new UserNotExistException("Administrador não encontrado!"));
        nullAwareBeanUtilsBean.copyProperties(administrator, administratorUpdate);
        administratorRepository.save(administrator);
        return administratorUpdate;
    }

    @Override
    public void deleteAdministrator(Long idAdmin) {
        administratorRepository.deleteById(idAdmin);
    }
}