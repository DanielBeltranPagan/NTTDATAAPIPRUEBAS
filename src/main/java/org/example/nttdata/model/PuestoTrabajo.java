package org.example.nttdata.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "puesto_trabajo")
public class PuestoTrabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_puesto")
    private Integer idPuesto;

    @Column(name = "tiene_ordenador")
    private Boolean tieneOrdenador;

    @ManyToOne
    @JoinColumn(name = "id_planta")
    @JsonIgnore
    private Planta planta;

    @OneToMany(mappedBy = "puestoTrabajo")
    @JsonBackReference
    private List<ReservaPuesto> reservasPuesto;
}
