package com.murillons.store.repositories;

import com.murillons.store.entities.Paper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaperRepository extends JpaRepository<Paper, Long> {
    Paper findByName(String name);
}