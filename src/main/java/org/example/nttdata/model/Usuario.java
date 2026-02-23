package org.example.nttdata.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    private String contrasena;

    private String correo;

    private String rango;

    @ManyToOne
    @JoinColumn(name = "id_sucursal")
    private Sucursal sucursal;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<ReservaPuesto> reservasPuesto;

    @OneToMany(mappedBy = "usuario")
    @JsonBackReference
    private List<ReservaSala> reservasSala;
}
