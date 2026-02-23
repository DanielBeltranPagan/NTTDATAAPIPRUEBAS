package org.example.nttdata.repository;

import org.example.nttdata.model.ReservaPuesto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface ReservaPuestoRepository extends JpaRepository<ReservaPuesto, Integer> {

    // Busca si existe una reserva para un puesto de trabajo en una fecha específica
    boolean existsByPuestoTrabajo_IdPuestoAndFecha(Integer idPuesto, LocalDate fecha);
}
