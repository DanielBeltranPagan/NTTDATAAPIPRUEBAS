package org.example.nttdata.mapper;

import org.example.nttdata.dto.PuestoTrabajoDTO;
import org.example.nttdata.model.PuestoTrabajo;
import org.example.nttdata.repository.ReservaPuestoRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PuestoTrabajoMapper {

    private final ReservaPuestoRepository reservaPuestoRepository;

    public PuestoTrabajoMapper(ReservaPuestoRepository reservaPuestoRepository) {
        this.reservaPuestoRepository = reservaPuestoRepository;
    }

    public PuestoTrabajoDTO toDto(PuestoTrabajo puesto, LocalDate fecha) {
        if (puesto == null) return null;

        PuestoTrabajoDTO dto = new PuestoTrabajoDTO();
        dto.setIdPuesto(puesto.getIdPuesto());
        dto.setTieneOrdenador(puesto.getTieneOrdenador());
        dto.setIdPlanta(puesto.getPlanta().getIdPlanta());

        boolean estaOcupado = reservaPuestoRepository.existsByPuestoTrabajo_IdPuestoAndFecha(
                puesto.getIdPuesto(),
                fecha
        );

        dto.setOcupado(estaOcupado);

        return dto;
    }
}