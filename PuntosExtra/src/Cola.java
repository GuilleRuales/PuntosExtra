import java.util.LinkedList;
import java.util.Queue;

public class Cola {
    private Queue<Productos> cola;

    public Cola() {
        this.cola = new LinkedList<>();
    }

    public void agregarProducto(Productos producto) {
        cola.add(producto);
    }

    public Productos buscarProductoPorCodigo(String codigo) {
        for (Productos producto : cola) {
            if (producto.getCodigo().equals(codigo)) {
                return producto;
            }
        }
        return null;
    }

    public boolean modificarProducto(String codigo, String nuevoNombre, double nuevoPrecio) {
        for (Productos producto : cola) {
            if (producto.getCodigo().equals(codigo)) {
                producto.setNombre(nuevoNombre);
                producto.setPrecio(nuevoPrecio);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarProducto(String codigo) {
        return cola.removeIf(producto -> producto.getCodigo().equals(codigo));
    }

    public boolean existeCodigoProducto(String codigo) {
        for (Productos producto : cola) {
            if (producto.getCodigo().equals(codigo)) {
                return true;
            }
        }
        return false;
    }

    public Queue<Productos> getCola() {
        return cola;
    }

    public void setCola(Queue<Productos> cola) {
        this.cola = cola;
    }


}