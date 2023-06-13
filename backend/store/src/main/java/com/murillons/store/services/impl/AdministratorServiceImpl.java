package com.murillons.store.services.impl;

import com.murillons.store.components.NullAwareBeanUtilsBean;
import com.murillons.store.dto.AdministratorRequest;
import com.murillons.store.dto.AdministratorUpdate;
import com.murillons.store.entities.Administrator;
import com.murillons.store.entities.Paper;
import com.murillons.store.repositories.AdministratorRepository;
import com.murillons.store.repositories.PaperRepository;
import com.murillons.store.services.AdministratorService;
import com.murillons.store.services.exceptions.DataAlreadyExistException;
import com.murillons.store.services.exceptions.UserNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.Set;

@Service
public class AdministratorServiceImpl implements AdministratorService {
    @Autowired
    private AdministratorRepository administratorRepository;

    @Autowired
    private NullAwareBeanUtilsBean nullAwareBeanUtilsBean;

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Administrator saveAdministrator(AdministratorRequest administratorRequest) {
        boolean emailVerified = administratorRepository.findByEmail(administratorRequest.getEmail()) != null;
        boolean cpfVerified = administratorRepository.findByCpf(administratorRequest.getCpf()) != null;
        Paper paper = paperRepository.findByName("ADMIN");

        if (emailVerified)
            throw new DataAlreadyExistException("Esse e-mail já está cadastrado.");
        else if (cpfVerified)
            throw new DataAlreadyExistException("Esse CPF já está cadastrado.");

        administratorRequest.addPaper(paper);

        Administrator administrator = Administrator.builder()
                .name(administratorRequest.getName())
                .email(administratorRequest.getEmail())
                .password(passwordEncoder.encode(administratorRequest.getPassword()))
                .cpf(administratorRequest.getCpf())
                .papers(administratorRequest.getPapers())
                .created(LocalDateTime.now())
                .build();

        return administratorRepository.save(administrator);
    }

    @Override
    public AdministratorUpdate updateAdministrator(Long idAdmin, AdministratorUpdate administratorUpdate) throws InvocationTargetException, IllegalAccessException {
        Administrator administrator = administratorRepository.findById(idAdmin)
                .orElseThrow(() -> new UserNotExistException("Administrador não encontrado!"));
        nullAwareBeanUtilsBean.copyProperties(administrator, administratorUpdate);
        administratorRepository.save(administrator);
        return administratorUpdate;
    }

    @Override
    public void deleteAdministrator(Long idAdmin) {
        administratorRepository.deleteById(idAdmin);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Administrator administrator = administratorRepository.findByEmail(username);
        if (administrator == null)
            throw new UsernameNotFoundException("Administrador não encontrado!");
        return administrator;
    }
}