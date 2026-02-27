package org.example.nttdata.repositories;

import org.example.nttdata.model.Sucursal;
import org.example.nttdata.model.Usuario;
import org.example.nttdata.repository.SucursalRepository;
import org.example.nttdata.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class UsuarioRepositoryIntegrationTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SucursalRepository sucursalRepository;

    @Test
    void findByCorreo_DebeRetornarUsuario() {
        Sucursal sucursal = new Sucursal();
        sucursal = sucursalRepository.save(sucursal);

        Usuario usuario = new Usuario();
        usuario.setCorreo("usuario@test.com");
        usuario.setContrasena("1234");
        usuario.setSucursal(sucursal);
        usuarioRepository.save(usuario);

        Optional<Usuario> encontrado = usuarioRepository.findByCorreo("usuario@test.com");

        assertTrue(encontrado.isPresent());
        assertEquals("usuario@test.com", encontrado.get().getCorreo());
        assertEquals(sucursal.getIdSucursal(), encontrado.get().getSucursal().getIdSucursal());
    }

    @Test
    void findByCorreo_NoExiste_DebeRetornarVacio() {
        Optional<Usuario> encontrado = usuarioRepository.findByCorreo("noexiste@test.com");
        assertFalse(encontrado.isPresent());
    }
}