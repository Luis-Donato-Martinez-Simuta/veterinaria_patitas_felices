package rest.duenios.modelo;

public class UsuarioJSON {
    String usuario;
    String password;

    public UsuarioJSON(String usuario, String password){
        this.usuario = usuario;
        this.password = password;
    }

    public UsuarioJSON(){}

    public String getUsuario(){
        return usuario;
    }

    public void setUsuario(String usuario){
        this.usuario = usuario;
    }

    public String getPassword(){
        return  password;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
