package org.example.nttdata.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "planta")
public class Planta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_planta")
    private Integer idPlanta;

    @ManyToOne
    @JoinColumn(name = "id_sucursal")
    private Sucursal sucursal;

    @OneToMany(mappedBy = "planta")
    @JsonManagedReference
    private List<Sala> salas;

    @OneToMany(mappedBy = "planta")
    @JsonManagedReference
    private List<PuestoTrabajo> puestosTrabajo;
}
