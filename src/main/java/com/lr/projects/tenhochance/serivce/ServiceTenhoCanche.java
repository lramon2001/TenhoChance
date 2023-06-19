package com.lr.projects.tenhochance.serivce;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lr.projects.tenhochance.entity.Candidato;
import com.lr.projects.tenhochance.repository.CandidatoRepository;
import com.lr.projects.tenhochance.utils.Classificador.Classificador;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class ServiceTenhoCanche {

    @Autowired
    private Classificador classificador;

    @Autowired
    private CandidatoRepository candidatoRepository;

    private void atualizarAprovacaoCandidatos(Page<Candidato> candidatos) {
        this.classificador.atualizarAprovacaoCandidatos(candidatos);
    }

    public Page<Candidato> buscaAprovadosSistemaUniversal(Pageable pageable, Long cursoId) {
        Page<Candidato> pageCandidatos = this.candidatoRepository.buscaCandidatosSistemaUniversal(cursoId,pageable);
        this.atualizarAprovacaoCandidatos(pageCandidatos);
        return pageCandidatos;
    }

    public Page<Candidato> buscaCandidatosSistemaNegros(Pageable pageable, Long cursoId) {
        return this.candidatoRepository.buscaCandidatosSistemaNegros(cursoId,pageable);
    }

    public Page<Candidato> buscaCandidatosSistemaEscolaPublicaBaixaRendaPpi(Pageable pageable, Long cursoId) {
        return this.candidatoRepository.buscaCandidatosSistemaEscolaPublicaBaixaRendaPpi(cursoId,pageable);
    }

    Page<Candidato> buscaCandidatosSistemaEscolaPublicaBaixaRendaPpiPcd(Pageable pageable, Long cursoId) {
        return this.candidatoRepository.buscaCandidatosSistemaEscolaPublicaBaixaRendaPpiPcd(cursoId,pageable);
    }

    Page<Candidato> buscaCandidatosSistemaEscolaPublicaBaixaRenda(Pageable pageable, Long cursoId) {
        return this.candidatoRepository.buscaCandidatosSistemaEscolaPublicaBaixaRenda(cursoId,pageable);
    }

    Page<Candidato> buscaCandidatosSistemaEscolaPublicaBaixaRendaPcd(Pageable pageable, Long cursoId) {
        return this.candidatoRepository.buscaCandidatosSistemaEscolaPublicaBaixaRendaPcd(cursoId,pageable);
    }

    Page<Candidato> buscaCandidatosSistemaEscolaPublicaNaoBaixaRendaPpi(Pageable pageable, Long cursoId){
        return this.candidatoRepository.buscaCandidatosSistemaEscolaPublicaNaoBaixaRendaPpi(cursoId,pageable);
    }

    Page<Candidato> buscaCandidatosSistemaEscolaPublicaNaoBaixaRendaPpiPcd(Pageable pageable, Long cursoId) {
        return this.candidatoRepository.buscaCandidatosSistemaEscolaPublicaNaoBaixaRendaPpiPcd(cursoId,pageable);
    }

    Page<Candidato> buscaCandidatosSistemaEscolaPublicaNaoBaixaRenda(Pageable pageable, Long cursoId) {
        return this.candidatoRepository.buscaCandidatosSistemaEscolaPublicaNaoBaixaRenda(cursoId,pageable);
    }

    Page<Candidato> buscaCandidatosSistemaEscolaPublicaNaoBaixaRendaPcd(Pageable pageable, Long cursoId) {
        return this.candidatoRepository.buscaCandidatosSistemaEscolaPublicaNaoBaixaRendaPcd(cursoId,pageable);
    }

}
