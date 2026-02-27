package org.example.nttdata.services;

import org.example.nttdata.dto.UsuarioDTO;
import org.example.nttdata.mapper.UsuarioMapper;
import org.example.nttdata.model.Sucursal;
import org.example.nttdata.model.Usuario;
import org.example.nttdata.repository.SucursalRepository;
import org.example.nttdata.repository.UsuarioRepository;
import org.example.nttdata.service.impl.UsuarioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private SucursalRepository sucursalRepository;

    @Mock
    private UsuarioMapper usuarioMapper;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    private Usuario usuario;
    private UsuarioDTO usuarioDTO;
    private Sucursal sucursal;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setCorreo("test@mail.com");
        usuario.setContrasena("1234");

        usuarioDTO = new UsuarioDTO();
        usuarioDTO.setIdUsuario(1);
        usuarioDTO.setCorreo("test@mail.com");
        usuarioDTO.setContrasena("1234");

        sucursal = new Sucursal();
        sucursal.setIdSucursal(10);
    }


    @Test
    void obtenerUsuarioPorCorreoYValidarContrasena_Exito() {
        // GIVEN
        when(usuarioRepository.findByCorreo("test@mail.com")).thenReturn(Optional.of(usuario));
        when(usuarioMapper.toDto(usuario)).thenReturn(usuarioDTO);

        // WHEN
        UsuarioDTO result = usuarioService.obtenerUsuarioPorCorreoYValidarContrasena("test@mail.com", "1234");

        // THEN
        assertNotNull(result);
        assertEquals("test@mail.com", result.getCorreo());
        verify(usuarioRepository).findByCorreo("test@mail.com");
        verify(usuarioMapper).toDto(usuario);
    }

    @Test
    void obtenerUsuarioPorCorreoYValidarContrasena_ErrorUsuarioNoEncontrado() {
        when(usuarioRepository.findByCorreo("test@mail.com")).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> usuarioService.obtenerUsuarioPorCorreoYValidarContrasena("test@mail.com", "1234"));

        assertEquals("Usuraio no encontrado.", ex.getMessage());
    }

    @Test
    void obtenerUsuarioPorCorreoYValidarContrasena_ErrorContrasenaIncorrecta() {
        when(usuarioRepository.findByCorreo("test@mail.com")).thenReturn(Optional.of(usuario));

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> usuarioService.obtenerUsuarioPorCorreoYValidarContrasena("test@mail.com", "wrongpass"));

        assertEquals("Contraseña incorrecta.", ex.getMessage());
    }


    @Test
    void cambiarIdSucursalUsuario_Exito() {
        // GIVEN
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
        when(sucursalRepository.findById(10)).thenReturn(Optional.of(sucursal));
        when(usuarioRepository.saveAndFlush(usuario)).thenReturn(usuario);

        // WHEN
        Boolean result = usuarioService.cambiarIdSucursalUsuario(1, 10);

        // THEN
        assertTrue(result);
        verify(usuarioRepository).findById(1);
        verify(sucursalRepository).findById(10);
        verify(usuarioRepository).saveAndFlush(usuario);
    }

    @Test
    void cambiarIdSucursalUsuario_ErrorUsuarioNoEncontrado() {
        when(usuarioRepository.findById(1)).thenReturn(Optional.empty());

        Boolean result = usuarioService.cambiarIdSucursalUsuario(1, 10);

        assertFalse(result);
    }

    @Test
    void cambiarIdSucursalUsuario_ErrorSucursalNoEncontrada() {
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
        when(sucursalRepository.findById(10)).thenReturn(Optional.empty());

        Boolean result = usuarioService.cambiarIdSucursalUsuario(1, 10);

        assertFalse(result);
    }
}