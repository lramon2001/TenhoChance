package com.lr.projects.tenhochance.api.controllers.universal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lr.projects.tenhochance.serivce.ServiceTenhoCanche;

@RestController
@RequestMapping("/universal")
public class UniversalController {

    @Autowired
    private ServiceTenhoCanche serviceTenhoCanche;

    @GetMapping("/candidatos")
    public ResponseEntity<Page> obterCandidatosSistemaUniversal(@RequestParam Long cursoId,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(serviceTenhoCanche.buscaAprovadosSistemaUniversal(pageable, cursoId));
    }

}
