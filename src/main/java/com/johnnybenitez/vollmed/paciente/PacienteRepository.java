package com.johnnybenitez.vollmed.paciente;

import com.johnnybenitez.vollmed.medico.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
