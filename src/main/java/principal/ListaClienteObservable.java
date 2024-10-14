package principal;



public class ListaClienteObservable {
    private int idCliente;
    private String nombre;
    private String direccion;
    private String email;
    private String estado;
    private String fechaAfil;
    private int nit;
    private String registrado;
    private String telefono;
    private String username;

    // Constructor
    public ListaClienteObservable(int idCliente, String nombre, String direccion, String email, String estado,
                                  String fechaAfil, int nit, String registrado, String telefono, String username) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.direccion = direccion;
        this.email = email;
        this.estado = estado;
        this.fechaAfil = fechaAfil;
        this.nit = nit;
        this.registrado = registrado;
        this.telefono = telefono;
        this.username = username;
    }

    // Getters y Setters
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getFechaAfil() { return fechaAfil; }
    public void setFechaAfil(String fechaAfil) { this.fechaAfil = fechaAfil; }

    public int getNit() { return nit; }
    public void setNit(int nit) { this.nit = nit; }

    public String getRegistrado() { return registrado; }
    public void setRegistrado(String registrado) { this.registrado = registrado; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}

