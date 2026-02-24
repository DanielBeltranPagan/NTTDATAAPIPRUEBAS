package org.example.nttdata.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.nttdata.controller.UsuarioRestController;
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

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioRestController.class)
public class UsuarioRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    private UsuarioDTO usuarioResponseDTO;
    private LoginDTO loginDTO;

    @BeforeEach
    void setUp() {
        // Preparar el DTO de respuesta esperado
        usuarioResponseDTO = new UsuarioDTO();
        usuarioResponseDTO.setIdUsuario(1);
        usuarioResponseDTO.setCorreo("test@nttdata.com");
        usuarioResponseDTO.setRango("ADMIN");

        // Preparar el DTO de entrada para el login
        loginDTO = new LoginDTO();
        loginDTO.setId(1);
        loginDTO.setContrasena("password123");
    }

    @Test
    void validarUsuario_DebeRetornarUsuario_CuandoCredencialesSonValidas() throws Exception {
        // GIVEN: Cuando el servicio reciba id 1 y pass "password123", devuelve nuestro DTO
        when(usuarioService.obtenerUsuarioYValidarContrasena(anyInt(), anyString()))
                .thenReturn(usuarioResponseDTO);

        // WHEN & THEN
        mockMvc.perform(post("/api/usuarios/1/validar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idUsuario").value(1))
                .andExpect(jsonPath("$.correo").value("test@nttdata.com"))
                .andExpect(jsonPath("$.rango").value("ADMIN"));
    }

    @Test
    void cambiarSucursal_DebeRetornarTrue_CuandoExito() throws Exception {
        // GIVEN: El servicio devuelve true si el cambio fue exitoso
        when(usuarioService.cambiarIdSucursalUsuario(1, 100)).thenReturn(true);

        // WHEN & THEN
        mockMvc.perform(put("/api/usuarios/1/sucursal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("100")) // Enviamos el Integer directamente en el body
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void cambiarSucursal_DebeRetornarBadRequest_CuandoFalla() throws Exception {
        // GIVEN: El servicio devuelve false si algo sale mal
        when(usuarioService.cambiarIdSucursalUsuario(1, 999)).thenReturn(false);

        // WHEN & THEN
        mockMvc.perform(put("/api/usuarios/1/sucursal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("999"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("false"));
    }
}