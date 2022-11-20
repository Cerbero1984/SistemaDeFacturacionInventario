package modelo;

/**
 *
 * @author Ezequiel R
 */
public class Usuario {
    //atributos
    private int idUsuario;
    private String Nombre;
    private String Apellido;
    private String usuario;
    private String password;
    private String Telefono;
    private int estado;
    // constructor

    public Usuario() {
        this.idUsuario = 0;
        this.Nombre = "";
        this.Apellido = "";
        this.usuario = "";
        this.password = "";
        this.Telefono = "";
        this.estado = 0;

    }
    //set and get

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

}
