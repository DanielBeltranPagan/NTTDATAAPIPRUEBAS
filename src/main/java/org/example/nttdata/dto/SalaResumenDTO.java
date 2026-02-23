package org.example.nttdata.dto;

import lombok.Data;
import org.example.nttdata.model.ReservaSala;
import java.util.List;

@Data
public class SalaResumenDTO {
    private Integer cantidadAsientos;
    private Boolean proyector;
}