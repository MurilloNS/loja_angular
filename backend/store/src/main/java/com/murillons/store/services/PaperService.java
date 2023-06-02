package com.murillons.store.services;

import com.murillons.store.entities.Paper;

public interface PaperService {
    Paper savePaper(Paper paper);

    Paper updatePaper(Long idPaper, Paper paper);

    void deletePaper(Long idPaper);
}