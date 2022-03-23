package rest.duenios.modelo;

import rest.duenios.modelo.Mascota;

public class UsuarioMascota extends Usuario{
    private Mascota[] mascotas;

    public UsuarioMascota() {
    	
    }

    public UsuarioMascota(Mascota[] mascotas) {
        this.mascotas = mascotas;
    }

    public UsuarioMascota(int idUsuario, String nombrePersonal, String userName, String password, int edad, String tipoUsuariom, String telefono, String direccion, Mascota[] mascotas) {
        super(idUsuario, nombrePersonal, userName, password, edad, tipoUsuariom, telefono, direccion);
        this.mascotas = mascotas;
    }

    public UsuarioMascota(int idUsuario, String nombrePersonal, String userName, String password, int edad, String tipoUsuariom, String telefono, String direccion) {
    	super(idUsuario, nombrePersonal, userName, password, edad, tipoUsuariom, telefono, direccion);
    }

    public Mascota[] getMascotas() {
        return mascotas;
    }

    public void setMascotas(Mascota[] mascotas) {
        this.mascotas = mascotas;
    }
}
