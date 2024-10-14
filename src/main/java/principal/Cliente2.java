package principal;

public class Cliente2 {
    private int id;
    private String nombre;
    private String direccion;
    private String email;
    private String telefono;
    private String bodega;
    private String capacidad;
    private int valor;

    // Constructor
    public Cliente2(int id, String nombre, String direccion, String email, String telefono, String bodega, String capacidad, int valor) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
        this.bodega = bodega;
        this.capacidad = capacidad;
        this.valor = valor;
    }

    // Getters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDireccion() { return direccion; }
    public String getEmail() { return email; }
    public String getTelefono() { return telefono; }
    public String getBodega() { return bodega; }
    public String getCapacidad() { return capacidad; }
    public int getValor() { return valor; }
}

