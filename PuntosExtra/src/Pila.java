import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

public class Pila {

    private Stack<Productos> productos;
    public Pila(){
        productos=new Stack<>();
    }

    public void insertar(Productos elementoNuevo){ //Esta es la nueva de insertar elemento
        productos.push(elementoNuevo);
    }
    public String listarTodosLosElementos(){
        String resultado= "";
        for(int i=productos.size()-1; i>=0;i--){
            resultado+=productos.get(i).toString();
        }
        return resultado;
    }
    public Productos removerElemento() throws Exception{
        if (productos.isEmpty()) {
            throw new Exception("Pila sin elementos"); //Agregue esta mensaje porque x, mas facil xd
        }
        return productos.pop();
    }

    public Productos peek() {
        if (productos.isEmpty()) {
            throw new EmptyStackException();
        }
        return productos.get(productos.size() - 1);
    }

    public boolean isEmpty() {
        return productos.isEmpty();
    }
    public int size() {
        return productos.size();
    }
    public Stack<Productos> buscarCodigo(String codigo){
        Stack<Productos> busqueda = new Stack<>();
        for(Productos p:productos){
            if(p.getCodigo().contains(codigo)){
                busqueda.add(p);

            }
        }return busqueda;
    }

}