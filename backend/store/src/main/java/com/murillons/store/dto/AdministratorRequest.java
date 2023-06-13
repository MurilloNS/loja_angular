package com.murillons.store.dto;

import com.murillons.store.entities.Paper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdministratorRequest {
    private String name;
    @Email
    private String email;
    private String password;
    @CPF
    private String cpf;
    private Set<Paper> papers = new HashSet<>();

    public void addPaper(Paper papers) {
        this.papers.add(papers);
    }

    public void removePaper(Paper papers) {
        this.papers.remove(papers);
    }
}