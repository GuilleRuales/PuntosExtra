import java.util.PriorityQueue;

public class ColaPrioridad {
    PriorityQueue<Productos> productosF;
    public ColaPrioridad(){
        productosF = new PriorityQueue<Productos>();
    }

    public void encolar(Productos producto){
        for (Productos p:productosF){
            if (p.getCodigo().equals(producto.getCodigo())) {
                throw new IllegalArgumentException("Ya existe un producto con ese codigo");
            }
        }
        productosF.add(producto);
    }

    public Productos buscarProducto(String codigo) throws Exception{
        for(Productos p:productosF){
            if(p.getCodigo().equals(codigo)){
                return p;
            }
        }
        throw new Exception("No se encontro el producto");
    }

    public void editarProducto(Productos producto, String nombre, int precio){
        Productos producto2 = new Productos();
        producto2.setNombre(nombre);
        producto2.setCodigo(producto.getCodigo());
        producto2.setPrecio(precio);
        productosF.remove(producto);
        productosF.add(producto2);
    }

    public void eliminarProducto(String codigo)throws Exception{
        Productos producto = buscarProducto(codigo);
        productosF.remove(producto);
    }

    public void desencolar(){
        productosF.poll();
    }


    public PriorityQueue<Productos> getProductosF() {
        return productosF;
    }

    public void setProductosF(PriorityQueue<Productos> productosF) {
        this.productosF = productosF;
    }

    @Override
    public String toString() {
        return "Ferreteria{" +
                "productosF=" + productosF +
                '}';
    }
}