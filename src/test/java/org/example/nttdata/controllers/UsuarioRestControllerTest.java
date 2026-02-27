package org.example.nttdata.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.nttdata.controller.UsuarioRestController;
import org.example.nttdata.dto.ActualizarSucursalDTO;
import org.example.nttdata.dto.LoginDTO;
import org.example.nttdata.dto.UsuarioDTO;
import org.example.nttdata.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioRestController.class)
class UsuarioRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    private LoginDTO loginDTO;
    private UsuarioDTO usuarioDTO;
    private ActualizarSucursalDTO actualizarSucursalDTO;

    @BeforeEach
    void setUp() {
        // DTO login
        loginDTO = new LoginDTO();
        loginDTO.setCorreo("test@mail.com");
        loginDTO.setContrasena("1234");

        // DTO respuesta usuario
        usuarioDTO = new UsuarioDTO();
        usuarioDTO.setIdUsuario(1);
        usuarioDTO.setCorreo("test@mail.com");
        usuarioDTO.setContrasena("1234");
        usuarioDTO.setRango("ADMIN");

        // DTO actualizar sucursal
        actualizarSucursalDTO = new ActualizarSucursalDTO();
        actualizarSucursalDTO.setIdSucursal(10);
    }

    @Test
    void validarUsuario_Exito_DebeRetornar200() throws Exception {
        // GIVEN
        when(usuarioService.obtenerUsuarioPorCorreoYValidarContrasena(anyString(), anyString()))
                .thenReturn(usuarioDTO);

        // WHEN & THEN
        mockMvc.perform(post("/api/usuarios/validar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idUsuario").value(1))
                .andExpect(jsonPath("$.correo").value("test@mail.com"));

        verify(usuarioService).obtenerUsuarioPorCorreoYValidarContrasena(anyString(), anyString());
    }

    @Test
    void cambiarSucursal_Exito_DebeRetornar200() throws Exception {
        // GIVEN
        when(usuarioService.cambiarIdSucursalUsuario(1, 10)).thenReturn(true);

        // WHEN & THEN
        mockMvc.perform(put("/api/usuarios/1/sucursal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(actualizarSucursalDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(usuarioService).cambiarIdSucursalUsuario(1, 10);
    }

    @Test
    void cambiarSucursal_Error_DebeRetornar400() throws Exception {
        // GIVEN
        when(usuarioService.cambiarIdSucursalUsuario(1, 10)).thenReturn(false);

        // WHEN & THEN
        mockMvc.perform(put("/api/usuarios/1/sucursal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(actualizarSucursalDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("false"));

        verify(usuarioService).cambiarIdSucursalUsuario(1, 10);
    }
}