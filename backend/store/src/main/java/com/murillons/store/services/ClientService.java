package com.murillons.store.services;

import com.murillons.store.dto.ClientRequest;
import com.murillons.store.entities.Client;

public interface ClientService {
    Client saveClient(ClientRequest clientRequest);
}
