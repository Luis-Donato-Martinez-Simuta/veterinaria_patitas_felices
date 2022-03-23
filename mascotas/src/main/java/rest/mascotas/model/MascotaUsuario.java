package rest.mascotas.model;

import java.util.List;

import rest.mascotas.*;

public class MascotaUsuario extends Usuario { 
	    private List<Mascota> mascotas;

	    public MascotaUsuario() {
	    	
	    }

	    public MascotaUsuario(Usuario usuario, List<Mascota> mascotas) {
	    	super(usuario);
	        this.mascotas = mascotas;
	    }

		public List<Mascota> getMascotas() {
			return mascotas;
		}

		public void setMascotas(List<Mascota> mascotas) {
			this.mascotas = mascotas;
		}

	    
 
  }
  
 
