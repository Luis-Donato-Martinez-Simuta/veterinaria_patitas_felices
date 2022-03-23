package rest.Repositorio;

import org.springframework.data.repository.CrudRepository;
import rest.Modelo.Cita;

import java.util.List;

public interface CitaRepository extends CrudRepository<Cita, Integer> {
    List<Cita> findAll();
    Cita findByIdCita(int idCita);
    List<Cita>findAllByFecha(String fecha);
    //List<Cita>findAllByIdUsuario(int IdUsuario);
    List<Cita>findAllByIdMascota(int IdMascota);
    Cita save(Cita cita);
    void delete(Cita cita);
}