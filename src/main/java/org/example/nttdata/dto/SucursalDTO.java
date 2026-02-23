package org.example.nttdata.dto;

import lombok.Data;
import org.example.nttdata.model.Planta;
import org.example.nttdata.model.Usuario;

import java.util.List;

@Data
public class SucursalDTO {
    private Integer idSucursal;
    private String ubicacion;
    private List<Planta> plantas;
    private List<Usuario> usuarios;
}
