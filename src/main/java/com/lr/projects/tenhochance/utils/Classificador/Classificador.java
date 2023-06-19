package com.lr.projects.tenhochance.utils.Classificador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lr.projects.tenhochance.entity.Candidato;
import com.lr.projects.tenhochance.repository.CandidatoRepository;

@Service
public class Classificador {

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Autowired
    private Buscador buscador;

    @Transactional
    public void atualizarAprovacaoCandidatos(Page<Candidato> candidatosPage) {
        List<Candidato> candidatos = candidatosPage.getContent();
        int ultimoAprovadoIndex = buscador.buscaBinariaCandidatoAprovado(candidatos);

        for (int i = 0; i <= ultimoAprovadoIndex; i++) {
            Candidato candidato = candidatos.get(i);
            candidato.setAprovado(true);
            candidatoRepository.save(candidato);
        }

        for (int i = ultimoAprovadoIndex + 1; i < candidatos.size(); i++) {
            Candidato candidato = candidatos.get(i);
            candidato.setAprovado(false);
            candidatoRepository.save(candidato);
        }
    }

}
