package org.example.nttdata.mapper;

import org.example.nttdata.dto.PuestoTrabajoDTO;
import org.example.nttdata.model.PuestoTrabajo;
import org.example.nttdata.repository.ReservaPuestoRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PuestoTrabajoMapper {

    // Ya no necesitas el ReservaPuestoMapper (que causaba bucles)
    // Necesitas el REPOSITORIO para consultar la disponibilidad real
    private final ReservaPuestoRepository reservaPuestoRepository;

    public PuestoTrabajoMapper(ReservaPuestoRepository reservaPuestoRepository) {
        this.reservaPuestoRepository = reservaPuestoRepository;
    }

    // Añadimos el parámetro LocalDate fecha
    public PuestoTrabajoDTO toDto(PuestoTrabajo puesto, LocalDate fecha) {
        if (puesto == null) return null;

        PuestoTrabajoDTO dto = new PuestoTrabajoDTO();
        dto.setIdPuesto(puesto.getIdPuesto());
        dto.setTieneOrdenador(puesto.getTieneOrdenador());
        dto.setIdPlanta(puesto.getPlanta().getIdPlanta());

        // LÓGICA PROFESIONAL: Consultamos al repositorio si hay una reserva ese día
        boolean estaOcupado = reservaPuestoRepository.existsByPuestoTrabajo_IdPuestoAndFecha(
                puesto.getIdPuesto(),
                fecha
        );

        dto.setOcupado(estaOcupado);

        return dto;
    }
}