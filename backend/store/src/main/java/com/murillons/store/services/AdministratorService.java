package com.murillons.store.services;

import com.murillons.store.dto.AdministratorRequest;
import com.murillons.store.dto.AdministratorUpdate;
import com.murillons.store.entities.Administrator;

import java.lang.reflect.InvocationTargetException;

public interface AdministratorService {
    Administrator saveAdministrator(AdministratorRequest administratorRequest);

    AdministratorUpdate updateAdministrator(Long idAdmin, AdministratorUpdate administratorUpdate) throws InvocationTargetException, IllegalAccessException;

    void deleteAdministrator(Long idAdmin);
}