package com.johnnybenitez.vollmed.domain.medico;

import com.johnnybenitez.vollmed.domain.consulta.Consulta;
import com.johnnybenitez.vollmed.domain.direccion.DatosDireccion;
import com.johnnybenitez.vollmed.domain.paciente.DatosRegistroPaciente;
import com.johnnybenitez.vollmed.domain.paciente.Paciente;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    private EntityManager em;

    @Test
    @DisplayName("Debería devolver null cuando el médico buscado existe pero no está disponible en la fecha dada")
    void elegirMedicoAleatorioDisponibleEnLaFechaEscenario1() {
        var lunesSiguiente = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        var medico = registrarMedico("Dr. Juan Perez", "juan@voll.med", "12345678", Especialidad.CARDIOLOGIA);
        var paciente = registrarPaciente("Maria Gomez", "mariag@voll.med", "87654321");
        registrarConsulta(medico, paciente, lunesSiguiente);

        var medicoLibre = medicoRepository.elegirMedicoAleatorioDisponibleEnLaFecha(Especialidad.CARDIOLOGIA, lunesSiguiente);
        assertThat(medicoLibre).isNull();
    }

    private void registrarConsulta(Medico medico, Paciente paciente, LocalDateTime fecha){
        em.persist(new Consulta(null, medico, paciente, fecha, null));
    };

    private Medico registrarMedico(String nombre, String email, String documento, Especialidad especialidad){
        var medico = new Medico(datosMedico(nombre, email, documento, especialidad));
        em.persist(medico);
        return medico;
    };

    private Paciente registrarPaciente(String nombre, String email, String documento){
        var paciente = new Paciente(datosPaciente(nombre, email, documento));
        em.persist(paciente);
        return paciente;
    };

    private DatosRegistroMedico datosMedico(String nombre, String email, String documento, Especialidad especialidad){
        return new DatosRegistroMedico(
                nombre,
                email,
                "129856789",
                documento,
                especialidad,
                datosDireccion()
        );
    };

    private DatosRegistroPaciente datosPaciente(String nombre, String email, String docmuento){
        return new DatosRegistroPaciente(
                nombre,
                email,
                "123456789",
                docmuento,
                datosDireccion()
        );
    };

    private DatosDireccion datosDireccion(){
        return new DatosDireccion(
                "Calle Falsa",
                "123",
                "Apto 4",
                "Centro",
                "1234",
                "Ciudad",
                "Estado"
        );
    }
}