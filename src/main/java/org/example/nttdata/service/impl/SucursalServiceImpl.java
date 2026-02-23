package org.example.nttdata.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.nttdata.dto.SucursalDTO;
import org.example.nttdata.mapper.SucursalMapper;
import org.example.nttdata.model.Sucursal;
import org.example.nttdata.repository.SucursalRepository;
import org.example.nttdata.service.SucursalService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SucursalServiceImpl implements SucursalService {

    private final SucursalRepository sucursalRepository;
    private final SucursalMapper sucursalMapper;

    @Override
    public List<SucursalDTO> obtenerTodasLasSucursales() {

        List<Sucursal> sucursales = sucursalRepository.findAll();

        List<SucursalDTO> listaDtos = sucursales.stream()
                .map(sucursalMapper::toDto)
                .collect(Collectors.toList());

        return listaDtos;
    }
}