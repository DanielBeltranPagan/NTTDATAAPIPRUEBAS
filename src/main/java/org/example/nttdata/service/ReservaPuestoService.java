package org.example.nttdata.service;

import org.example.nttdata.dto.ReservaPuestoDTO;

public interface ReservaPuestoService {
    ReservaPuestoDTO crearReservaPuesto(ReservaPuestoDTO reservaPuestoDTO);
    void eliminarReservaPorIdReserva(Integer idReserva);
}
