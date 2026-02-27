//No funciona por tema de Doker
//package org.example.nttdata.repositories;
//
//import jakarta.persistence.EntityManager;
//import org.example.nttdata.model.Sucursal;
//import org.example.nttdata.model.Usuario;
//import org.example.nttdata.repository.SucursalRepository;
//import org.example.nttdata.repository.UsuarioRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
//import org.springframework.test.context.TestPropertySource;
//import org.testcontainers.containers.PostgreSQLContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@DataJpaTest
//@Testcontainers
//@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=create-drop"})
//public class UsuarioRepositoryContainersTest {
//
//    @Container
//    @ServiceConnection
//    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17.6-alpine");
//
//    @Autowired
//    private UsuarioRepository usuarioRepository;
//
//    @Autowired
//    private SucursalRepository sucursalRepository;
//
//    @Autowired
//    private EntityManager entityManager;
//
//    @Test
//    void eliminarUsuario_DebeEliminarUsuarioYVerificarSucursal_RealDB() {
//        Sucursal sucursal = new Sucursal();
//        sucursal = sucursalRepository.save(sucursal);
//
//        Usuario usuario = new Usuario();
//        usuario.setCorreo("testcontainers_test@nttdata.org");
//        usuario.setContrasena("secret123");
//        usuario.setSucursal(sucursal);
//
//        Usuario guardado = usuarioRepository.save(usuario);
//        Integer idSucursal = guardado.getSucursal().getIdSucursal();
//
//        usuarioRepository.delete(guardado);
//        usuarioRepository.flush();
//        entityManager.clear();
//
//        assertFalse(usuarioRepository.findById(guardado.getIdUsuario()).isPresent());
//
//        Sucursal sucursalEnBD = entityManager.find(Sucursal.class, idSucursal);
//        assertNotNull(sucursalEnBD, "La sucursal debería seguir existiendo después de eliminar el usuario");
//    }
//}