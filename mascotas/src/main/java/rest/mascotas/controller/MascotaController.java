package rest.mascotas.controller;

import rest.mascotas.model.Usuario;
//import rest.mascotas.model.MascotaDuenio
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import rest.mascotas.model.Mascota;
import rest.mascotas.model.MascotaUsuario;
import rest.mascotas.repository.MascotaRepository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

// Esto esta corriendo en el puesto 7777

@RestController
@CrossOrigin(origins = "*")
public class MascotaController {

    @Autowired
    MascotaRepository mascotaRepository;

    @Autowired
    RestTemplate restTemplate;
    
    
    @GetMapping(value="/mascota_por_usuario/{IdUsuario}")
    public MascotaUsuario getMascotaPorIdUsuario(@PathVariable("IdUsuario") int IdUsuario){  
    	System.out.print("Id Usuario: ");
    	System.out.print(IdUsuario);
    	Usuario usuario = restTemplate.getForObject("http://localhost:8888/usuari_por_id/"+IdUsuario , Usuario.class);
    	List<Mascota> mascotas;
    	mascotas = mascotaRepository.findByIdUsuario(IdUsuario);	
    	MascotaUsuario mascotaUsuario = new MascotaUsuario(usuario, mascotas);
        return mascotaUsuario;
    }
    
    @GetMapping(value = "/mascota_por_Id/{IdMascota}")
    public Mascota getMascotaConDuenio(@PathVariable("IdMascota") int IdMascota){
        Mascota mascota = mascotaRepository.findByIdMascota(IdMascota);        
        return mascota;
    }
    
    @PostMapping(value = "/guardar_mascota")
    public Mascota addMascota(@RequestBody Mascota mascota){
        return mascotaRepository.save(mascota);
    }
    
    @PostMapping(value = "/eliminar_mascota/{IdMascota}")
    public Boolean deleteMascota(@PathVariable("IdMascota") int IdMascota) {
        Mascota mascota = mascotaRepository.findByIdMascota(IdMascota);
        if (mascota != null) {
            mascotaRepository.delete(mascota);
            return true;
        }
        return false;
    }

    
    /*
    @GetMapping(value = "/listByIdDuenio/{idDuenio}")
    public List<Mascota> getMascotaByIdDuenio(@PathVariable("idDuenio") int idDuenio){
        return mascotaRepository.findByIdDuenio(idDuenio);
    }
    
    
    @GetMapping(value = "/listByIdMedicamento/{idMedicamento}")
    public List<Mascota> getMascotaByIdMedicamento(@PathVariable("idMedicamento") int idMedicamento){
        return mascotaRepository.findByIdMedicamento(idMedicamento);
    }
    
    
    @GetMapping(value = "/listByIdCita/{idCita}")
    public Mascota getMascotaByIdCita(@PathVariable("idCita") int idCita){
        return mascotaRepository.findByIdCita(idCita);
    }
    
    
    

    @GetMapping(value = "/listMascotas")
    public List<Mascota> getListMascota(){
        return mascotaRepository.findAll();
    }

    @GetMapping(value="/mascota")
    public Mascota getMascota(){
        return mascotaRepository.findByIdMascota(1);
    }

    @PostMapping(value="/mascota/tipo")
    public List<Mascota> getMascotasByTipo(@RequestBody Mascota mascota){
        return mascotaRepository.findByTipo(mascota.getTipo());
    }

    //@GetMapping(value = "/mascotaConDuenio/{idMascota}")
    @GetMapping(value = "/mascotaConDuenio/{idMascota}")
    public MascotaDuenio getMascotaConDuenio(@PathVariable("idMascota") int idMascota){
        Mascota mascota = mascotaRepository.findByIdMascota(idMascota);
        MascotaDuenio mascotaDuenio= null;
        if (mascota!=null){
            mascotaDuenio = new MascotaDuenio(mascota.getIdMascota(), mascota.getNombre(), mascota.getTipo(),mascota.getEdad(), mascota.getIdDuenio(), mascota.getIdCita(), mascota.getIdMedicamento(), mascota.getFechaIngreso(), mascota.getRazon());
            Duenio duenio = restTemplate.getForObject("http://localhost:18080/duenio/"+ mascota.getIdDuenio(), Duenio.class);
            mascotaDuenio.setDuenio(duenio);

        }
        return mascotaDuenio;
    }

    @PostMapping(value = "/mascota/nombre")
    public List<Mascota> getMascotaByNombre(@RequestBody Mascota mascota){
        return mascotaRepository.findAllByNombre(mascota.getNombre());
    }

    @PostMapping(value = "/mascota/add")
    public Mascota addMascota(@RequestBody Mascota mascota){
        return mascotaRepository.save(mascota);
    }

    @PostMapping(value = "/mascota/update")
    public Mascota updateMascota(@RequestBody Mascota mascota){
        if(mascotaRepository.findByIdMascota(mascota.getIdMascota())!=null){
            return mascotaRepository.save(mascota);
        }
        return null;
    }

    @PostMapping(value = "/mascota/delete")
    public Boolean deleteMascota(@RequestBody Mascota mascota) {
        Mascota m = mascotaRepository.findByIdMascota(mascota.getIdMascota());
        if (m != null) {
            mascotaRepository.delete(m);
            return true;
        }
        return null;
    }
    */
}