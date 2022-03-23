package rest.duenios.modelo;

import javax.persistence.*;

@Entity
@Table (name="usuario")
public class Usuario {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)    
    private int idUsuario;
    
    @Column(name = "NombrePersonal")
    private String nombrePersonal;
    
    @Column(name = "UserName")
    private String  userName;
    
    @Column(name = "Password")
    private String password;
    
    @Column(name = "Edad")
    private int edad;

    //@Column(name = "TipoUsuario")
    //private String  tipoUsuario;
    
    @Column(name = "Telefono")
    private String telefono;
    
    @Column(name = "Direccion")
    private String direccion;

	public Usuario(int idUsuario, String nombrePersonal, String userName, String password, int edad,
			String tipoUsuario, String telefono, String direccion) {
		
		this.idUsuario = idUsuario;
		this.nombrePersonal = nombrePersonal;
		this.userName = userName;
		this.password = password;
		this.edad = edad;
		//this.tipoUsuario = tipoUsuario;
		this.telefono = telefono;
		this.direccion = direccion;
	}

	public Usuario() {
		
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombrePersonal() {
		return nombrePersonal;
	}

	public void setNombrePersonal(String nombrePersonal) {
		this.nombrePersonal = nombrePersonal;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	/*public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}*/

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nombrePersonal=" + nombrePersonal + ", userName=" + userName
				+  "]";
	}
	
	
    

}
