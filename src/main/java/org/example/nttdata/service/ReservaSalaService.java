package org.example.nttdata.service;

import org.example.nttdata.dto.ReservaSalaDTO;

public interface ReservaSalaService {
    ReservaSalaDTO crearReservaSala(ReservaSalaDTO reservaSalaDTO);
    ReservaSalaDTO actualizarSala(Integer id, ReservaSalaDTO dto);
    void eliminarReservaPorIdReserva(Integer idReserva);
}
