package rest.duenios.controlador;

import rest.duenios.modelo.UsuarioMascota;
import rest.duenios.modelo.Mascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rest.duenios.modelo.Usuario;
import rest.duenios.repositorio.UsuarioRepository;
import org.springframework.web.client.RestTemplate;


import java.util.*;

//Esto esta corriendo en el puesto 8888

@RestController
@CrossOrigin(origins = "*")
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping(value = "/listaUsuarios")
    public List<Usuario> getListaUsuarios(){
        return usuarioRepository.findAll();
    }
    
    @GetMapping(value="/usuari_por_id/{IdUsuario}")
    public Usuario getDuenio(@PathVariable("IdUsuario") int IdUsuario){
    	//System.out.println(IdUsuario);
    	Usuario usuario = new Usuario();
    	usuario = usuarioRepository.findByIdUsuario(IdUsuario);    	
		return usuario;
    }
    
    @PostMapping(value="/logeo/{username}/{password}")
    public Usuario logue(@PathVariable("username") String username, @PathVariable("password") String password) {    	
    	List<Usuario> usuarios = usuarioRepository.findAll();
    	Usuario usuario = new Usuario();
    	
    	for (Usuario u : usuarios) {    	
			if(username.compareTo(u.getUserName()) == 0 && password.compareTo(u.getPassword()) == 0){
				usuario = u;
			}
		}
    	
    	return usuario;
    }
    
    
    @PostMapping(value = "/nuevo_usuario")
    public Usuario addDuenio(@RequestBody Usuario usuario){
    	
        return usuarioRepository.save(usuario);
    }
    
    /*
    @GetMapping(value = "/duenioConMascotas/{idDuenio}")
    public List<Mascota> getDuenioConMascotas(@PathVariable("idDuenio") int idDuenio){
    	//System.out.println("Buscando las mascotas del dueño");
    	List<Mascota> listaMascotas = null;
        Duenio duenio =  duenioRepository.findByIdDuenio(idDuenio);
        DuenioMascota duenioMascota= null;
        if (duenio != null){
            duenioMascota = new DuenioMascota(duenio.getIdDuenio(), duenio.getNombre(), duenio.getTelefono(), duenio.getDireccion());
            Mascota[] mascotas  =restTemplate.getForObject("http://localhost:7777/listByIdDuenio/"+duenioMascota.getIdDuenio(), Mascota[].class);
            //duenioMascota.setMascotas(mascotas);
            listaMascotas = Arrays.asList(mascotas);
        }
        return listaMascotas;
    }
    @GetMapping(value = "/duenio/direccion")
    public List<Duenio> getDuenioByDireccion(@RequestBody Duenio duenio){
        return duenioRepository.findDuenioByDireccion(duenio.getDireccion());
    }
    @GetMapping(value="/duenio/{idDuenio}")
    public Duenio getDuenio(@PathVariable("idDuenio") int idDuenio){
    	System.out.println(idDuenio);
    	Duenio duenio = duenioRepository.findByIdDuenio(idDuenio);    	
		return duenio;

    }

    @PostMapping(value = "/duenio/telefono")
    public List<Duenio> getDuenioByCountry(@RequestBody Duenio duenio){
        return duenioRepository.findAllByTelefono(duenio.getTelefono());
    }
    
    @PostMapping(value = "/duenio/nombre")
    public List<Duenio> getDuenioByName(@RequestBody Duenio duenio){
    	System.out.println(duenio.getNombre());
        return duenioRepository.findAllByNombre(duenio.getNombre());
    }
    
    
    @PostMapping(value = "/duenio/add")
    public Duenio addDuenio(@RequestBody Duenio duenio){
        return duenioRepository.save(duenio);
    }
    @PostMapping(value = "/duenio/update")
    public Duenio updateDuenio(@RequestBody Duenio duenio){
        if(duenioRepository.findByIdDuenio(duenio.getIdDuenio()) != null){
            return duenioRepository.save(duenio);
        }
        return null;
    }
    @PostMapping(value = "/duenio/delete")
    public Boolean deleteDuenio(@RequestBody Duenio duenio){
        Duenio d = duenioRepository.findByIdDuenio(duenio.getIdDuenio());
        if(d != null){
            duenioRepository.delete(d);
            return true;
        }
        return null;
    }
    */
}
