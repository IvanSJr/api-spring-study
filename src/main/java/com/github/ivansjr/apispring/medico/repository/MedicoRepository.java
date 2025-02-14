package com.github.ivansjr.apispring.medico.repository;

import com.github.ivansjr.apispring.medico.entity.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAlLByAtivoIsTrue(Pageable pageable);
}
