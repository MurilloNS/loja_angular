package com.murillons.store.services;

import com.murillons.store.entities.Paper;

import java.util.List;

public interface PaperService {
    Paper savePaper(Paper paper);

    Paper updatePaper(Long idPaper, Paper paper);

    void deletePaper(Long idPaper);

    List<Paper> listPapers();
}