package com.murillons.store.repositories;

import com.murillons.store.entities.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
    Administrator findByEmail(String email);

    Administrator findByCpf(String cpf);
}