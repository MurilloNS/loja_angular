package com.murillons.store.services.impl;

import com.murillons.store.entities.Paper;
import com.murillons.store.repositories.AdministratorRepository;
import com.murillons.store.repositories.PaperRepository;
import com.murillons.store.services.PaperService;
import com.murillons.store.services.exceptions.DataAlreadyExistException;
import com.murillons.store.services.exceptions.PermissionDeniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaperServiceImpl implements PaperService {
    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private AdministratorRepository administratorRepository;

    @Override
    public Paper savePaper(Paper paper, Long idAdmin) {
        boolean adminVerified = administratorRepository.findById(idAdmin).isEmpty();
        boolean paperVerified = paperRepository.findByName(paper.getName()) != null;

        if(adminVerified)
            throw new PermissionDeniedException("Você não possui a permissão necessária para realizar essa ação.");
        else if(paperVerified)
            throw new DataAlreadyExistException("Esse Papel já está cadastrado!");
        return paperRepository.save(paper);
    }
}