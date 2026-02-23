package org.example.nttdata.dto;

import lombok.Data;
import org.example.nttdata.model.Planta;
import org.example.nttdata.model.ReservaSala;

import java.util.List;

@Data
public class SalaDTO {
    private Integer idSala;
    private Integer cantidadAsientos;
    private Boolean proyector;
    private Planta planta;
    private List<ReservaSala> reservasSala;
}
