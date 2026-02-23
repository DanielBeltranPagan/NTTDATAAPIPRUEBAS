package org.example.nttdata.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "sala")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sala")
    private Integer idSala;

    @Column(name = "cantidad_asientos")
    private Integer cantidadAsientos;

    private Boolean proyector;

    @ManyToOne
    @JoinColumn(name = "id_planta")
    @JsonBackReference
    private Planta planta;

    @OneToMany(mappedBy = "sala")
    @JsonBackReference
    private List<ReservaSala> reservasSala;
}
