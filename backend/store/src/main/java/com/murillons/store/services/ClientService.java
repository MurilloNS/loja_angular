package com.murillons.store.services;

import com.murillons.store.dto.ClientRequest;
import com.murillons.store.dto.ClientUpdate;
import com.murillons.store.entities.Client;

import java.lang.reflect.InvocationTargetException;

public interface ClientService {
    Client saveClient(ClientRequest clientRequest);

    ClientUpdate updateClient(Long idClient, ClientUpdate clientUpdate) throws InvocationTargetException, IllegalAccessException;

    void deleteClient(Long idClient);
}
