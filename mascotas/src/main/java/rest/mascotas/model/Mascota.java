package rest.mascotas.model;
import javax.persistence.*;

@Entity
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMascota;

    @Column(name = "NombreMascota")
    private String NombreMascota;
    
    @Column(name = "IdUsuario")
    private int idUsuario;

    @Column(name = "TipoMascota")
    private String tipo;

    @Column(name = "Edad")
    private int edad;

	

	public Mascota(int idMascota, String nombre, int idUsuario, String tipo, int edad) {
		super();
		this.idMascota = idMascota;
		this.NombreMascota = nombre;
		this.idUsuario = idUsuario;
		this.tipo = tipo;
		this.edad = edad;
	}

	public Mascota() {
		
	}

	public int getIdMascota() {
		return idMascota;
	}

	public void setIdMascota(int idMascota) {
		this.idMascota = idMascota;
	}



	public String getNombreMascota() {
		return NombreMascota;
	}

	public void setNombreMascota(String nombreMascota) {
		NombreMascota = nombreMascota;
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
		return "Mascota [idMascota=" + idMascota + ", nombre=" + NombreMascota + ", idUsuario=" + idUsuario + ", tipo=" + tipo
				+ ", edad=" + edad + "]";
	}
 
	

}