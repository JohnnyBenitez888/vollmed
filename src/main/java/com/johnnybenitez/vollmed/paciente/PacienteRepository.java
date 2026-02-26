package com.johnnybenitez.vollmed.paciente;

import com.johnnybenitez.vollmed.medico.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Page<Paciente> findAllByActivoTrue(Pageable paginacion);
}
