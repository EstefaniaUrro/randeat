package backend.modelo;

public class Usuario {
    private int idUsuario;
    private String correoElectronico;
    private String contrasena;
    private String nombre;
    private String telefono;
    private String poblacion;
    private String direccion;

    public Usuario() {

    }

    public Usuario(
        int idUsuario,
        String correoElectronico,
        String contrasena,
        String nombre,
        String telefono,
        String poblacion,
        String direccion
    ) {
        this.idUsuario = idUsuario;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.telefono = telefono;
        this.poblacion = poblacion;
        this.direccion = direccion;
    }

    public int getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCorreoElectronico() {
        return this.correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasena() {
        return this.contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPoblacion() {
        return this.poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}