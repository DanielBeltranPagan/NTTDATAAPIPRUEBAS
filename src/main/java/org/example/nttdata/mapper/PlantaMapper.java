package org.example.nttdata.mapper;

import jdk.jfr.Label;
import org.example.nttdata.dto.PlantaDTO;
import org.example.nttdata.dto.SucursalDTO;
import org.example.nttdata.model.Planta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class PlantaMapper {

    private final SucursalMapper sucursalMapper;

    @Autowired
    @Lazy
    public PlantaMapper(SucursalMapper sucursalMapper) {
        this.sucursalMapper = sucursalMapper;
    }

    public PlantaDTO toDto(Planta planta) {
        if (planta == null) return null;

        PlantaDTO dto = new PlantaDTO();
        dto.setIdPlanta(planta.getIdPlanta());

        // evitar bucle → no mapear toda la sucursal
        if (planta.getSucursal() != null) {
            SucursalDTO sucursalDTO = new SucursalDTO();
            sucursalDTO.setIdSucursal(planta.getSucursal().getIdSucursal());
            dto.setSucursal(sucursalDTO);
        }

        dto.setSalas(planta.getSalas());
        dto.setPuestosTrabajo(planta.getPuestosTrabajo());

        return dto;
    }
}
