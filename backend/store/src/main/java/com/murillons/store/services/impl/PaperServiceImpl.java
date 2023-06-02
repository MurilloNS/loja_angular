package com.murillons.store.services.impl;

import com.murillons.store.entities.Paper;
import com.murillons.store.repositories.PaperRepository;
import com.murillons.store.services.PaperService;
import com.murillons.store.services.exceptions.DataAlreadyExistException;
import com.murillons.store.services.exceptions.UserNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaperServiceImpl implements PaperService {
    @Autowired
    private PaperRepository paperRepository;

    @Override
    public Paper savePaper(Paper paper) {
        boolean paperVerified = paperRepository.findByName(paper.getName()) != null;

        if(paperVerified)
            throw new DataAlreadyExistException("Esse Papel já está cadastrado!");
        return paperRepository.save(paper);
    }

    @Override
    public Paper updatePaper(Long idPaper, Paper paper) {
        boolean paperVerified = paperRepository.findByName(paper.getName()) != null;
        if(paperVerified)
            throw new DataAlreadyExistException("Esse Papel já está cadastrado!");

        Paper editedPaper = paperRepository.findById(idPaper)
                .orElseThrow(() -> new UserNotExistException("Papel não encontrado!"));

        editedPaper.setName(paper.getName());

        return paperRepository.save(editedPaper);
    }
}