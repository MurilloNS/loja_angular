package com.murillons.store.services.impl;

import com.murillons.store.components.NullAwareBeanUtilsBean;
import com.murillons.store.dto.ClientRequest;
import com.murillons.store.dto.ClientUpdate;
import com.murillons.store.entities.Client;
import com.murillons.store.repositories.ClientRepository;
import com.murillons.store.services.ClientService;
import com.murillons.store.services.exceptions.ClientNotExistException;
import com.murillons.store.services.exceptions.CpfExistsException;
import com.murillons.store.services.exceptions.EmailExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    NullAwareBeanUtilsBean nullAwareBeanUtilsBean;

    @Override
    public Client saveClient(ClientRequest clientRequest) {
        Client emailVerified = clientRepository.findByEmail(clientRequest.getEmail());
        Client cpfVerified = clientRepository.findByCpf(clientRequest.getCpf());

        if (emailVerified != null)
            throw new EmailExistsException("Esse e-mail já está cadastrado.");
        else if (cpfVerified != null)
            throw new CpfExistsException("Esse cpf já está cadastrado.");

        Client client = Client.builder()
                .name(clientRequest.getName())
                .email(clientRequest.getEmail())
                .password(clientRequest.getPassword())
                .cpf(clientRequest.getCpf())
                .created(LocalDateTime.now())
                .build();

        return clientRepository.save(client);
    }

    @Override
    public ClientUpdate updateClient(Long idClient, ClientUpdate clientUpdate) throws InvocationTargetException, IllegalAccessException {
        Client client = clientRepository.findById(idClient)
                .orElseThrow(() -> new ClientNotExistException("Cliente não encontrado!"));
        nullAwareBeanUtilsBean.copyProperties(client, clientUpdate);
        clientRepository.save(client);
        return clientUpdate;
    }

    @Override
    public void deleteClient(Long idClient) {
        clientRepository.deleteById(idClient);
    }
}
