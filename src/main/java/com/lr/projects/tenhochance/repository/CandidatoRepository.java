package com.lr.projects.tenhochance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lr.projects.tenhochance.entity.Candidato;

public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
    
}
