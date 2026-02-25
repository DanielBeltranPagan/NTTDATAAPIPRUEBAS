package org.example.nttdata.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class ActualizarSucursalDTO {

    @JsonProperty("id_sucursal")
    private Integer idSucursal;

    // Constructor vacío necesario para Spring
    public ActualizarSucursalDTO() {}

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }
}