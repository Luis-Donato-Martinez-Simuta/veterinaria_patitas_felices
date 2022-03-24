package rest.duenios.controlador;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import rest.duenios.modelo.UsuarioJSON;
import rest.duenios.modelo.UsuarioMascota;
import rest.duenios.modelo.Mascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rest.duenios.modelo.Usuario;
import rest.duenios.repositorio.UsuarioRepository;
import org.springframework.web.client.RestTemplate;


import java.util.*;
import java.util.stream.Collectors;

//Esto esta corriendo en el puesto 8888

@RestController
@CrossOrigin(origins = "*")
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping(value = "/API/listaUsuarios")
    public List<Usuario> getListaUsuarios(){
        return usuarioRepository.findAll();
    }
    
    @GetMapping(value="/usuario_por_id/{IdUsuario}")
    public Usuario getDuenio(@PathVariable("IdUsuario") int IdUsuario){
    	//System.out.println(IdUsuario);
    	Usuario usuario = new Usuario();
    	usuario = usuarioRepository.findByIdUsuario(IdUsuario);    	
		return usuario;
    }

    /*
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
    */
    /*
    @PostMapping(value = "/nuevo_usuario")
    public Usuario addDuenio(@RequestBody Usuario usuario){

        return usuarioRepository.save(usuario);
    }*/

    private String getJWTToken(String username){
        String secretKey = "secret";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("petJWT")
                //.setId("idUsuario")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 6000000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();
        return "Bearer " + token;
    }

    /*@PostMapping(value = "/loginUser")
    public Usuario getUser(@RequestBody UsuarioJSON usuario) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        Usuario user = new Usuario();

        for (Usuario u : usuarios) {
            if(usuario.getUsuario().compareTo(u.getUserName()) == 0 && usuario.getPassword().compareTo(u.getPassword()) == 0){
                user = u;
                System.out.println("Usuario: " + user);
            }else {
                System.err.println("Datos Incorrectos!");
            }
        }

        return user;
    }*/
    @PostMapping(value = "/loginUser")
    public List<String> getUser(@RequestBody UsuarioJSON usuario){
        Usuario user = usuarioRepository.findByUserNameAndPassword(usuario.getUsuario(), usuario.getPassword());
        List<String> userLogin = new ArrayList<>();
        if(user != null){
            System.out.println("Usuario ENCONTRADO: " + user);
            //return user.getNombrePersonal();
            String token = getJWTToken(usuario.getUsuario());
            //return  token;
            //userLogin.add(String.valueOf(user.getIdUsuario()));
            userLogin.add(Integer.toString(user.getIdUsuario()));
            userLogin.add(token);

            return userLogin;
        }else{
            System.err.println("No se encontro el usuario");
            return null;
        }

    }

}
