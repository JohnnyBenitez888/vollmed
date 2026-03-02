package com.johnnybenitez.vollmed.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByActivoTrue(Pageable paginacion);

    /*@Query("Select m from Medico m where m.activo = 1 and m.especialidad = :especialidad and m.id not in (Select c.medico.id from Consulta c where c.fecha = :fecha) order by rand() limit 1")
    Medico elegirMedicoAleatorioDisponibleEnLaFecha(Especialidad especialidad, LocalDateTime fecha);*/

    @Query("Select m.activo from medicos m where m.id = :idMedico")
    boolean findActivoById(Long idMedico);
}
