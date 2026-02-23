package org.example.nttdata.repository;

import org.example.nttdata.model.ReservaSala;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface ReservaSalaRepository extends JpaRepository<ReservaSala, Integer> {

    // Busca si existe una reserva para una sala en una fecha específica
    boolean existsBySalaIdSalaAndFecha(Integer id, LocalDate fecha);
}
