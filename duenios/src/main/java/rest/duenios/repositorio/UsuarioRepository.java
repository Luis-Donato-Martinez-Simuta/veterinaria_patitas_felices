package rest.duenios.repositorio;

import org.springframework.data.repository.CrudRepository;
import rest.duenios.modelo.Usuario;

import java.util.List;

public interface UsuarioRepository extends CrudRepository <Usuario, Integer> {
    List<Usuario> findAll();
    Usuario findByIdUsuario(int IdUsuario);
    Usuario save(Usuario duenio);
    /*
    List<Usuario> findDuenioByDireccion(String direccion);
    Usuario findByIdDuenio(int idDuenio);
    List<Usuario>findAllByTelefono(String telefono);
    List<Usuario>findAllByNombre(String nombre);
    
    void delete(Usuario duenio);
    */
}
