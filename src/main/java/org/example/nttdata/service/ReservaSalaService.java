package org.example.nttdata.service;

import org.example.nttdata.dto.ReservaSalaDTO;

public interface ReservaSalaService {
    ReservaSalaDTO crearReservaSala(ReservaSalaDTO reservaSalaDTO);
    void eliminarReservaPorIdReserva(Integer idReserva);
}
