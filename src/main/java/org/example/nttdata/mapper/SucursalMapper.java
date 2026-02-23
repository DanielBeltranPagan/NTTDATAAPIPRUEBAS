package org.example.nttdata.mapper;

import org.example.nttdata.dto.SucursalDTO;
import org.example.nttdata.model.Sucursal;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class SucursalMapper {

    private final PlantaMapper plantaMapper;
    private final UsuarioMapper usuarioMapper;

    // Inyección por constructor (el estándar de Spring)
    public SucursalMapper(PlantaMapper plantaMapper, UsuarioMapper usuarioMapper) {
        this.plantaMapper = plantaMapper;
        this.usuarioMapper = usuarioMapper;
    }

    public SucursalDTO toDto(Sucursal sucursal) {
        if (sucursal == null) return null;

        SucursalDTO dto = new SucursalDTO();
        dto.setIdSucursal(sucursal.getIdSucursal());
        dto.setUbicacion(sucursal.getUbicacion());

        dto.setPlantas(sucursal.getPlantas());
        dto.setUsuarios(sucursal.getUsuarios());

        return dto;
    }
}