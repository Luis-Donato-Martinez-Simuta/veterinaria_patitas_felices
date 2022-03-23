package rest.Modelo;

public class CitaMascota extends Cita{
    private Mascota mascota;

    public CitaMascota() {
    }

    public CitaMascota(int id, String fecha, String hora, String tipoServicio, int IdUsuario) {
        super(id, fecha, hora, tipoServicio,IdUsuario);
    }

    public CitaMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public CitaMascota(int id, String fecha, String hora, String tipoServicio, Mascota mascota, int IdUsuario) {
        super(id, fecha, hora, tipoServicio, IdUsuario);
        this.mascota = mascota;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }
}
