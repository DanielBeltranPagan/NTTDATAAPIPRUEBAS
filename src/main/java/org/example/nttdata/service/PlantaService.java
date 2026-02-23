package org.example.nttdata.service;

import org.example.nttdata.dto.PlantaDTO;

import java.time.LocalDate;

public interface PlantaService{
    PlantaDTO obtenerEstadoPlanta(Integer idPlanta, LocalDate fecha);
}
