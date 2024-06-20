import java.util.ArrayList;
import java.util.List;

public class Lista {

    private List<Productos> producto;

    public Lista() {
        producto = new ArrayList<Productos>();
    }

    public List<Productos> getProducto() {
        return producto;
    }

    public void setProducto(List<Productos> producto) {
        this.producto = producto;
    }

    public void adicionarElementos(Productos p) throws Exception {
            if (producto.isEmpty())
                producto.add(p);
            else {
                for (Productos pa : producto)
                    if(pa.getCodigo().equals(p.getCodigo()))
                        throw new Exception("Ese producto ya existe");
                producto.add(p);
            }
    }

    public Productos buscarProducto(String codigo) {
        for (Productos p : producto)
            if (p.getCodigo().equals(codigo))
                return p;
        return null;
    }

    public void editar(String codigo, String  nuevoNombre, double nuevoPrecio)  throws Exception {
        Productos productos = buscarProducto(codigo);
        if (productos != null) {
            productos.setNombre(nuevoNombre);
            productos.setPrecio(nuevoPrecio);
        } else {
            throw new Exception("No se encontró ningún producto con el código especificada: " + codigo);
        }
    }

    public List<Productos> buscarProductoPapeleria(String codigo) {
        List<Productos> resultado = new ArrayList<>();
        for (Productos p : producto) {
            if (p.getCodigo().equals(codigo)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public List<Productos> ordenarProductosPorCodigo() {
        List<Productos> lista = new ArrayList<>(producto);
        for (int i = 0; i < lista.size() - 1; i++) {
            for (int j = 0; j < lista.size() - i - 1; j++) {
                if (lista.get(j + 1).getCodigo().compareTo(lista.get(j).getCodigo()) < 0) {
                    Productos aux = lista.get(j + 1);
                    lista.set(j + 1, lista.get(j));
                    lista.set(j, aux);
                }
            }
        }
        return lista;
    }

    public int busquedaBinaria(List<Productos> listaOrdenada, String codigo) {
        int inicio = 0;
        int fin = listaOrdenada.size() - 1;
        while (inicio <= fin) {
            int medio = inicio + (fin - inicio) / 2;
            if (listaOrdenada.get(medio).getCodigo().equals(codigo))
                return medio;

            if (listaOrdenada.get(medio).getCodigo().compareTo(codigo) < 0)
                inicio = medio + 1;
            else
                fin = medio - 1;
        }
        return -1;
    }

    public List<Productos> ordenarProductosPorCodigoBurbuja(){
        List<Productos> lista = new ArrayList<>(producto);
        for (int i = 0; i < lista.size() - 1; i++) {
            for (int j = 0; j < lista.size() - i - 1; j++) {
                if (lista.get(j + 1).getCodigo().compareTo(lista.get(j).getCodigo()) < 0) {
                    Productos aux = lista.get(j + 1);
                    lista.set(j + 1, lista.get(j));
                    lista.set(j, aux);
                }
            }
        }
        return lista;
    }

    public List<Productos> ordenarProductosPorPrecioInsercion() {
        List<Productos> lista = new ArrayList<>(producto);
        for (int p = 1; p < lista.size(); p++) {
            Productos aux = lista.get(p);
            int j = p - 1;
            while (j >= 0 && aux.getPrecio() < lista.get(j).getPrecio()) {
                lista.set(j + 1, lista.get(j));
                j--;
            }
            lista.set(j + 1, aux);
        }
        return lista;
    }

    public void eliminarProducto(String codigo) throws Exception {
        boolean encontrado = false;
        for (int i = 0; i < producto.size(); i++) {
            Productos p = producto.get(i);
            if (p.getCodigo().equals(codigo)) {
                producto.remove(i);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            throw new Exception("No se encontró ningún producto con el código especificado: " + codigo);
        }
    }

    public int sumarTotalProductos (){
        return totalProductos(0);
    }

    private int totalProductos (int indice){
        if(producto.size()==indice)
            return 0;
        else{
            return 1+totalProductos(indice+1);
        }
    }



}
