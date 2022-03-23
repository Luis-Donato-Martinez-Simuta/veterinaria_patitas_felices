package rest.duenios.modelo;
import javax.persistence.*;

@Entity
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMascota;

    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "IdUsuario")
    private int idUsuario;

    @Column(name = "TipoMascota")
    private String tipo;

    @Column(name = "Edad")
    private int edad;

	public Mascota(int idMascota, String nombre, int idUsuario, String tipo, int edad) {
		super();
		this.idMascota = idMascota;
		this.nombre = nombre;
		this.idUsuario = idUsuario;
		this.tipo = tipo;
		this.edad = edad;
	}

	public Mascota() {
		super();
	}

	public int getIdMascota() {
		return idMascota;
	}

	public void setIdMascota(int idMascota) {
		this.idMascota = idMascota;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	@Override
	public String toString() {
		return "Mascota [idMascota=" + idMascota + ", nombre=" + nombre + ", idUsuario=" + idUsuario + ", tipo=" + tipo
				+ ", edad=" + edad + "]";
	}
 
	

}