public class Productos implements Comparable<Productos>{

    private String nombre;
    private String codigo;
    private double precio;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Productos() {
    }

    public Productos(String nombre, String codigo, double precio) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "\nNombre: "+nombre+
                "\nCodigo: "+codigo+
                "\nPrecio: "+precio + "\n";
    }

    @Override
    public int compareTo(Productos o) {
        if (precio<o.getPrecio()){
            return 1;
        }
        else if (precio>o.getPrecio()){
            return -1;
        }
        else
            return 0;
    }


    //tipos: ferreteria(Naomi), alimentos(Mateo), limpieza(Richie), papeleria(Guille, Emilio)
    //botones: agregar, buscar por codigo, editar, eliminar


}