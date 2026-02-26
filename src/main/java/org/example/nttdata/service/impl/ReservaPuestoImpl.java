package org.example.nttdata.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.nttdata.dto.ReservaPuestoDTO;
import org.example.nttdata.dto.ReservaSalaDTO;
import org.example.nttdata.mapper.ReservaPuestoMapper;
import org.example.nttdata.model.PuestoTrabajo;
import org.example.nttdata.model.ReservaPuesto;
import org.example.nttdata.model.ReservaSala;
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

    @Override
    public ReservaPuestoDTO actualizarPuesto(Integer id, ReservaPuestoDTO dto) {
        // 1. Buscamos la reserva actual en la base de datos
        ReservaPuesto reservaExistente = reservaPuestoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva de puesto no encontrada con ID: " + id));

        // 2. Modificamos solo lo que habéis pedido: fecha y hora
        reservaExistente.setFecha(dto.getFecha());
        reservaExistente.setHoraInicio(dto.getHoraInicio());

        // Si vuestro DTO también permite cambiar la hora de fin, descomentad la siguiente línea:
        // reservaExistente.setHoraFin(dto.getHoraFin());

        // 3. Guardamos los cambios
        ReservaPuesto guardada = reservaPuestoRepository.save(reservaExistente);

        // 4. Devolvemos el DTO actualizado usando vuestro mapper
        return reservaPuestoMapper.toDto(guardada);
    }
}
