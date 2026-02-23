package org.example.nttdata.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "sucursal")
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sucursal")
    private Integer idSucursal;

    private String ubicacion;

    @OneToMany(mappedBy = "sucursal")
    @JsonIgnore
    private List<Planta> plantas;

    @OneToMany(mappedBy = "sucursal")
    @JsonIgnore
    private List<Usuario> usuarios;
}
