package org.example.nttdata.dto;

import lombok.Data;
import org.example.nttdata.model.ReservaPuesto;
import org.example.nttdata.model.ReservaSala;

import java.util.List;

@Data
public class UsuarioDTO {
    private Integer idUsuario;
    private String contrasena;
    private String correo;
    private String rango;
    private SucursalDTO sucursal;
    private List<ReservaPuesto> reservasPuestos;
    private List<ReservaSala> reservasSalas;
}
