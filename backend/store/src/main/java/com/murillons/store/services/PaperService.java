package com.murillons.store.services;

import com.murillons.store.entities.Paper;

public interface PaperService {
    Paper savePaper(Paper paper, Long idAdmin);
}