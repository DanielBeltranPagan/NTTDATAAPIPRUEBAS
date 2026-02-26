package org.example.nttdata.service;

import org.example.nttdata.dto.ReservaPuestoDTO;

public interface ReservaPuestoService {
    ReservaPuestoDTO crearReservaPuesto(ReservaPuestoDTO reservaPuestoDTO);
    ReservaPuestoDTO actualizarPuesto(Integer id, ReservaPuestoDTO dto);
    void eliminarReservaPorIdReserva(Integer idReserva);
}
