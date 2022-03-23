package rest.Modelo;


import javax.persistence.*;

@Entity
@Table(name = "cita")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCita;

    @Column(name = "FechaCita")
    private String fecha;

    @Column(name = "HoraCita")
    private String hora;

    @Column(name = "TipoServicio")
    private String tipoServicio;
    
    @Column(name = "IdMascota")                    
    private int idMascota;


    public Cita(){}

    public Cita(int id, String fecha, String hora, String tipoServiciom, int idMascota) {
        this.idCita = id;
        this.fecha = fecha;
        this.hora = hora;
        this.idMascota = idMascota;
        this.tipoServicio = tipoServicio;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int id) {
        this.idCita = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

	public int getIdUsuario() {
		return idMascota;
	}

	public void setIdUsuario(int idMascota) {
		this.idMascota = idMascota;
	}
}
