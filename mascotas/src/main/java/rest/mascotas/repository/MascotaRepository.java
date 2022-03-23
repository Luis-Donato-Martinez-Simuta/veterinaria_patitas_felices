package rest.mascotas.repository;


import org.springframework.data.repository.CrudRepository;
import rest.mascotas.model.Mascota;

import java.util.List;

public interface MascotaRepository extends CrudRepository<Mascota, Integer> {
	
	
	Mascota findAllByIdUsuario(int IdUsuario);
	List<Mascota> findByIdUsuario(int Idusuario);
	Mascota findByIdMascota(int IdMascota);
	Mascota save(Mascota mascota);
	void deleteById(int IdMascota);
    /*
	List<Mascota> findAll();
    List<Mascota> findByIdDuenio(int idDuenio);
    List<Mascota> findByIdMedicamento(int idMedicamento);
    List<Mascota> findByTipo(String tipo);
    Mascota findByIdCita(int idCita);
    Mascota findByIdMascota(int idMascota);
    List<Mascota>findAllByNombre(String nombre);
    Mascota save(Mascota mascota);
    void delete(Mascota mascota);
    8*/
}