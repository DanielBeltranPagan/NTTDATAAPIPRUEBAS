package org.example.nttdata.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.nttdata.dto.PlantaDTO;
import org.example.nttdata.mapper.PlantaMapper;
import org.example.nttdata.model.Planta;
import org.example.nttdata.model.PuestoTrabajo;
import org.example.nttdata.model.Sala;
import org.example.nttdata.repository.PlantaRepository;
import org.example.nttdata.repository.ReservaPuestoRepository;
import org.example.nttdata.repository.ReservaSalaRepository;
import org.example.nttdata.service.PlantaService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlantaServiceImpl implements PlantaService {

    private final PlantaRepository plantaRepository;
    private final ReservaPuestoRepository reservaPuestoRepository;
    private final ReservaSalaRepository reservaSalaRepository;
    private final PlantaMapper plantaMapper;

    @Override
    public PlantaDTO obtenerEstadoPlanta(Integer idPlanta, LocalDate fecha){
        Planta planta = plantaRepository.findById(idPlanta)
                .orElseThrow(() -> new RuntimeException("Planta no encontrada."));

        List<Sala> salasLibres = planta.getSalas().stream()
                .filter(sala -> !reservaSalaRepository.existsBySalaIdSalaAndFecha(sala.getIdSala(), fecha))
                .collect(Collectors.toList());

        List<PuestoTrabajo> puestoTrabajoLibres = planta.getPuestosTrabajo().stream()
                .filter(puestoTrabajo -> !reservaPuestoRepository.existsByPuestoTrabajo_IdPuestoAndFecha(puestoTrabajo.getIdPuesto(), fecha))
                .collect(Collectors.toList());

        PlantaDTO dto = plantaMapper.toDto(planta);
        dto.setSalas(salasLibres);
        dto.setPuestosTrabajo(puestoTrabajoLibres);

        return dto;
    }
}
