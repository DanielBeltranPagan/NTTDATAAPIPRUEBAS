package org.example.nttdata.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.nttdata.dto.ReservaSalaDTO;
import org.example.nttdata.mapper.ReservaSalaMapper;
import org.example.nttdata.model.ReservaSala;
import org.example.nttdata.model.Sala;
import org.example.nttdata.model.Usuario;
import org.example.nttdata.repository.ReservaSalaRepository;
import org.example.nttdata.repository.SalaRepository;
import org.example.nttdata.repository.UsuarioRepository;
import org.example.nttdata.service.ReservaSalaService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservaSalaImpl implements ReservaSalaService {

    private final ReservaSalaRepository reservaSalaRepository;
    private final SalaRepository salaRepository;
    private final UsuarioRepository usuarioRepository;
    private final ReservaSalaMapper reservaSalaMapper;

    @Override
    public ReservaSalaDTO crearReservaSala(ReservaSalaDTO dto) {

        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Sala sala = salaRepository.findById(dto.getIdSala())
                .orElseThrow(() -> new RuntimeException("Sala no encontrada"));

        ReservaSala reservaSala = reservaSalaMapper.toEntity(dto, sala, usuario);

        ReservaSala guardada = reservaSalaRepository.save(reservaSala);

        return reservaSalaMapper.toDto(guardada);
    }

    @Override
    public void eliminarReservaPorIdReserva(Integer idReserva) {
        if (!reservaSalaRepository.existsById(idReserva)) {
            throw new RuntimeException("La reserva de sala con ID " + idReserva + " no existe.");
        }
        reservaSalaRepository.deleteById(idReserva);
    }
}
