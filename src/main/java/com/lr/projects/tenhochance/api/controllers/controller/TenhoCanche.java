package com.lr.projects.tenhochance.api.controllers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lr.projects.tenhochance.entity.Candidato;
import com.lr.projects.tenhochance.serivce.ServiceTenhoCanche;


@RestController
@RequestMapping("/tenho-canche")
public class TenhoCanche {

    @Autowired
    private ServiceTenhoCanche serviceTenhoCanche;

    @GetMapping("/universal")
    public ResponseEntity<Page<Candidato>> obterCandidatosSistemaUniversal(@RequestParam Long cursoId, Pageable pageable) {
        return ResponseEntity.ok(serviceTenhoCanche.buscaCandidatosSistemaUniversal(pageable, cursoId));
    }


    @GetMapping("/negros")
    public ResponseEntity<Page<Candidato>> obterCandidatosSistemaNegros(@RequestParam Long cursoId, Pageable pageable) {
        return ResponseEntity.ok(serviceTenhoCanche.buscaCandidatosSistemaNegros(pageable, cursoId));
    }

    @GetMapping("/escola-publica-baixa-renda-ppi")
     public ResponseEntity<Page<Candidato>> obterCandidatosSistemaEscolaPublicaBaixaRendaPpi(Pageable pageable, Long cursoId) {
        return ResponseEntity.ok(serviceTenhoCanche.buscaCandidatosSistemaEscolaPublicaBaixaRendaPpi(pageable,cursoId)); 
    }

    @GetMapping("/escola-publica-baixa-renda-ppi-pcd")
    ResponseEntity<Page<Candidato>> buscaCandidatosSistemaEscolaPublicaBaixaRendaPpiPcd(Pageable pageable, Long cursoId) {
         return ResponseEntity.ok(serviceTenhoCanche.buscaCandidatosSistemaEscolaPublicaBaixaRendaPpiPcd(pageable,cursoId)); 
    }

    @GetMapping("/escola-publica-baixa-renda")
    ResponseEntity<Page<Candidato>> buscaCandidatosSistemaEscolaPublicaBaixaRenda(Pageable pageable, Long cursoId) {
         return ResponseEntity.ok(serviceTenhoCanche.buscaCandidatosSistemaEscolaPublicaBaixaRenda(pageable,cursoId)); 
    }

    @GetMapping("/escola-publica-baixa-renda-pcd")
    ResponseEntity<Page<Candidato>> buscaCandidatosSistemaEscolaPublicaBaixaRendaPcd(Pageable pageable, Long cursoId) {
         return ResponseEntity.ok(serviceTenhoCanche.buscaCandidatosSistemaEscolaPublicaBaixaRendaPcd(pageable,cursoId)); 
    }

    @GetMapping("/escola-publica-nao-baixa-renda-ppi")
    ResponseEntity<Page<Candidato>> buscaCandidatosSistemaEscolaPublicaNaoBaixaRendaPpi(Pageable pageable, Long cursoId){
         return ResponseEntity.ok(serviceTenhoCanche.buscaCandidatosSistemaEscolaPublicaNaoBaixaRendaPpi(pageable,cursoId));
    }
    
    @GetMapping("/escola-publica-nao-baixa-renda-ppi-pcd")
    ResponseEntity<Page<Candidato>> buscaCandidatosSistemaEscolaPublicaNaoBaixaRendaPpiPcd(Pageable pageable, Long cursoId) {
         return ResponseEntity.ok(serviceTenhoCanche.buscaCandidatosSistemaEscolaPublicaNaoBaixaRendaPpiPcd(pageable,cursoId)); 
    }

    @GetMapping("/escola-publica-nao-baixa-renda")
    ResponseEntity<Page<Candidato>> buscaCandidatosSistemaEscolaPublicaNaoBaixaRenda(Pageable pageable, Long cursoId) {
         return ResponseEntity.ok(serviceTenhoCanche.buscaCandidatosSistemaEscolaPublicaNaoBaixaRenda(pageable,cursoId)); 
    }

    @GetMapping("/escola-publica-nao-baixa-renda-pcd")
    ResponseEntity<Page<Candidato>> buscaCandidatosSistemaEscolaPublicaNaoBaixaRendaPcd(Pageable pageable, Long cursoId) {
         return ResponseEntity.ok(serviceTenhoCanche.buscaCandidatosSistemaEscolaPublicaNaoBaixaRendaPcd(pageable,cursoId)); 
    }



    
    


}
