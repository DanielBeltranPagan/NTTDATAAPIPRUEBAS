package org.example.nttdata.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.nttdata.dto.ReservaPuestoDTO;
import org.example.nttdata.mapper.ReservaPuestoMapper;
import org.example.nttdata.model.PuestoTrabajo;
import org.example.nttdata.model.ReservaPuesto;
import org.example.nttdata.model.Usuario;
import org.example.nttdata.repository.PuestoTrabajoRepository;
import org.example.nttdata.repository.ReservaPuestoRepository;
import org.example.nttdata.repository.UsuarioRepository;
import org.example.nttdata.service.ReservaPuestoService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservaPuestoImpl implements ReservaPuestoService {

    private final ReservaPuestoRepository reservaPuestoRepository;
    private final PuestoTrabajoRepository puestoTrabajoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ReservaPuestoMapper reservaPuestoMapper;

    @Override
    public ReservaPuestoDTO crearReservaPuesto(ReservaPuestoDTO dto) {

        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        PuestoTrabajo puesto = puestoTrabajoRepository.findById(dto.getIdPuesto())
                .orElseThrow(() -> new RuntimeException("Puesto de trabajo no encontrado"));

        ReservaPuesto reservaPuesto = reservaPuestoMapper.toEntity(dto, puesto, usuario);

        ReservaPuesto guardada = reservaPuestoRepository.save(reservaPuesto);

        return reservaPuestoMapper.toDto(guardada);
    }

    @Override
    public void eliminarReservaPorIdReserva(Integer idReserva) {
        if (!reservaPuestoRepository.existsById(idReserva)) {
            throw new RuntimeException("La reserva con ID " + idReserva + " no existe.");
        }
        reservaPuestoRepository.deleteById(idReserva);
    }
}
