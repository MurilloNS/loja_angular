package com.murillons.store.repositories;

import com.murillons.store.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByEmail(String email);

    Client findByCpf(String cpf);
}
