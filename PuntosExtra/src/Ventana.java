import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Stack;

public class Ventana {
    private JPanel Ventanita;
    private JTabbedPane tabbedPane1;
    private JTextField textField2;
    private JTextField textField3;
    private JComboBox comboBox1;
    private JButton buttonAgregarLimpieza;
    private JButton buttonModificarLimpieza;
    private JButton buttonBuscarLimpieza;
    private JButton buttonEliminarLimpieza;
    private JList list1;
    private JButton buttonAgregarPapeleria;
    private JComboBox comboPapeleria;
    private JTextField textPapeleria1;
    private JTextField textPapeleria2;
    private JButton buttonBuscarPapeleria;
    private JButton buttonModificarPapeleria;
    private JButton buttonEliminarPapeleria;
    private JButton buttonAgregarFerreteria;
    private JButton buttonModificarFerreteria;
    private JButton buttonBuscarFerreteria;
    private JButton buttonEliminarFerreteria;
    private JComboBox comboHerramientas;
    private JTextField textCodigoF;
    private JTextField textPrecioF;
    private JTextArea textAreaFerreteria;
    private JTextArea textArea2;
    private JButton totalPapeleriaButton;
    private JButton ordenarInventarioPorCodigoButton;
    private JList list3;
    private JList list2;
    private JButton ordenarInventarioPorNombreButton;
    private JList list4;
    private JButton desencolarButton;
    private JButton buttonAgregarAlimento;
    private JButton buttonModificarAlimento;
    private JButton buttonBuscarAlimento;
    private JButton buttonEliminarAlimento;
    private JComboBox cboComida;
    private JTextField txtCodigo;
    private JTextField txtPrecio;
    private JTextArea txtTexto;
    private JTextArea textBuscar;

    private Pila pila = new Pila();
    private Cola colaProductos = new Cola();
    ColaPrioridad colaProducto = new ColaPrioridad();
    private Lista papeleria = new Lista();

    public Ventana() {

        buttonAgregarPapeleria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    papeleria.adicionarElementos(new Productos(comboPapeleria.getSelectedItem().toString(), textPapeleria1.getText(),
                            Double.parseDouble(textPapeleria2.getText())));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                llenarJlist(list1);
                limpiarDatosPapeleria();
            }
        });


        buttonModificarPapeleria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (list1.getSelectedIndex() != -1) {
                    Productos productoSeleccionado = (Productos) list1.getSelectedValue();
                    String codigoOriginal = productoSeleccionado.getCodigo();
                    String nuevoCodigo = textPapeleria1.getText().trim();

                    if (!nuevoCodigo.equals(codigoOriginal)) {
                        JOptionPane.showMessageDialog(null, "No se puede modificar el código de un producto existente");
                        llenarJlist(list1);
                        limpiarDatosPapeleria();
                        return;
                    }

                    String nuevoProducto = comboPapeleria.getSelectedItem().toString();
                    Double nuevoPrecio = Double.parseDouble(textPapeleria2.getText());

                    try {
                        papeleria.editar(codigoOriginal, nuevoProducto, nuevoPrecio);
                        llenarJlist(list1);
                        limpiarDatosPapeleria();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
            }
        });

        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(list1.getSelectedIndex() != -1) {
                    int indice = list1.getSelectedIndex();
                    Productos pa = papeleria.getProducto().get(indice);
                    comboPapeleria.setSelectedItem(pa.getNombre());
                    textPapeleria1.setText(pa.getCodigo());
                    textPapeleria2.setText(String.valueOf(pa.getPrecio()));
                }
            }
        });

        buttonBuscarPapeleria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    llenarJlistBusqueda(textPapeleria1.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Numero de Tracking invalido: ");
                }
                limpiarDatosPapeleria();
            }
        });

        buttonEliminarPapeleria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = textPapeleria1.getText().trim();
                if (!codigo.isEmpty()) {
                    try {
                        papeleria.eliminarProducto(codigo);
                        JOptionPane.showMessageDialog(null, "Producto eliminado correctamente");
                        llenarJlist(list1);
                        limpiarDatosPapeleria();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un código para eliminar el producto");
                }
                limpiarDatosPapeleria();
            }
        });


        buttonAgregarFerreteria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Productos producto = new Productos();
                producto.setNombre(comboHerramientas.getSelectedItem().toString());
                producto.setCodigo(textCodigoF.getText());
                producto.setPrecio(Integer.parseInt(textPrecioF.getText()));
                try {
                    colaProducto.encolar(producto);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                textAreaFerreteria.setText(colaProducto.getProductosF().toString());
                limpiarDatosFerreteria();
            }
        });

        buttonBuscarFerreteria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Productos producto = colaProducto.buscarProducto(textCodigoF.getText());
                    comboHerramientas.setSelectedItem(producto.getNombre());
                    textPrecioF.setText(String.valueOf(producto.getPrecio()));
                    textAreaFerreteria.setText(colaProducto.getProductosF().toString());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        buttonModificarFerreteria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Productos producto = colaProducto.buscarProducto(textCodigoF.getText());
                    colaProducto.editarProducto(producto, comboHerramientas.getSelectedItem().toString(), Integer.parseInt(textPrecioF.getText()));
                    textAreaFerreteria.setText(colaProducto.getProductosF().toString());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        buttonEliminarFerreteria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    colaProducto.eliminarProducto(textCodigoF.getText());
                    textAreaFerreteria.setText(colaProducto.getProductosF().toString());
                    limpiarDatosFerreteria();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        desencolarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colaProducto.desencolar();
                textAreaFerreteria.setText(colaProducto.getProductosF().toString());
            }
        });

        buttonAgregarAlimento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Todo lo de abajo sirve para guardar lo que escribe el usuario
                String nombre = cboComida.getSelectedItem().toString();
                String codigo = txtCodigo.getText();
                double precio = Double.parseDouble(txtPrecio.getText());
                //Esto guarda la lista
                Productos p = new Productos(nombre, codigo, precio);
                //Mensajito
                JOptionPane.showMessageDialog(null,"Se añadio un nuevo producto");
                pila.insertar(p);
                //Las funciones estan iguales
                actualizarLista();
                limpiar();
                //Esto imprime todo
                txtTexto.setText(pila.listarTodosLosElementos());
            }
        });

        buttonBuscarAlimento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Guarda el codigo
                String codigo = txtCodigo.getText();
                Stack<Productos> encontrar = pila.buscarCodigo(codigo);
                //Esto imprime lo encontrado
                textBuscar.setText(String.valueOf(encontrar));
            }
        });

        buttonEliminarAlimento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Bueno esto es un try catch, creo que ya sabes que pdo xd
                try {
                    String eliminar = pila.removerElemento().toString();
                    JOptionPane.showMessageDialog(null, "Se elimino con exito" + eliminar);
                    txtTexto.setText(pila.listarTodosLosElementos());
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
                }
            }
        });


        buttonAgregarLimpieza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Productos nuevoProducto = new Productos(comboBox1.getSelectedItem().toString(), textField2.getText(), Double.parseDouble(textField3.getText()));
                    if (colaProductos.existeCodigoProducto(nuevoProducto.getCodigo())) {
                        JOptionPane.showMessageDialog(null, "El código ya existe, por favor ingrese otro.");
                    } else {
                        colaProductos.agregarProducto(nuevoProducto);
                        llenarJTextArea();
                        JOptionPane.showMessageDialog(null, "Producto agregado correctamente.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: ingresa un precio válido.");
                }
            }
        });

        buttonModificarLimpieza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = JOptionPane.showInputDialog("Ingrese el código del producto a modificar:");
                if (codigo != null && !codigo.isEmpty()) {
                    Productos productoSeleccionado = colaProductos.buscarProductoPorCodigo(codigo);
                    if (productoSeleccionado != null) {
                        String nuevoNombre = comboBox1.getSelectedItem().toString();
                        double nuevoPrecio;
                        try {
                            nuevoPrecio = Double.parseDouble(textField3.getText());
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Error: ingresa un precio válido.");
                            return;
                        }

                        boolean modificado = colaProductos.modificarProducto(codigo, nuevoNombre, nuevoPrecio);
                        if (modificado) {
                            llenarJTextArea();
                            JOptionPane.showMessageDialog(null, "Producto modificado correctamente.");
                        } else {
                            JOptionPane.showMessageDialog(null, "No se encontró el producto con el código especificado.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró el producto con el código especificado.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese un código válido.");
                }
            }
        });

        buttonEliminarLimpieza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = JOptionPane.showInputDialog("Ingrese el código del producto a eliminar:");
                if (codigo != null && !codigo.isEmpty()) {
                    boolean eliminado = colaProductos.eliminarProducto(codigo);
                    if (eliminado) {
                        llenarJTextArea();
                        JOptionPane.showMessageDialog(null, "Producto eliminado correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró el producto con el código especificado.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese un código válido.");
                }
            }
        });

        buttonBuscarLimpieza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = JOptionPane.showInputDialog("Ingrese el código del producto a buscar:");
                if (codigo != null && !codigo.isEmpty()) {
                    Productos encontrado = colaProductos.buscarProductoPorCodigo(codigo);
                    if (encontrado != null) {
                        JOptionPane.showMessageDialog(null, "Producto encontrado:\n" + encontrado.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró ningún producto con el código especificado.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese un código válido.");
                }
            }
        });

        totalPapeleriaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "El número de productos de papelería registrados es: "+papeleria.sumarTotalProductos());
            }
        });

        ordenarInventarioPorCodigoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                llenarJlistCodigo(list3);
                llenarJlist(list4);
            }
        });

        ordenarInventarioPorNombreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                llenasJlistPrecio(list3);
                llenarJlist(list4);
            }
        });
    }

    public void llenarJlist(JList listaPapeleria){
        DefaultListModel dlm = new DefaultListModel<>();
        dlm.removeAllElements();
        for (Productos p: papeleria.getProducto())
            dlm.addElement(p);
        listaPapeleria.setModel(dlm);
    }

    public void llenarJlistBusqueda(String codigo) throws Exception {
        DefaultListModel dlm2 = new DefaultListModel<>();
        List<Productos> listaOrdenada = papeleria.ordenarProductosPorCodigo();
        int indiceEncontrado = papeleria.busquedaBinaria(listaOrdenada, codigo);

        if (indiceEncontrado != -1) {
            dlm2.addElement(listaOrdenada.get(indiceEncontrado));
        } else {
            throw new Exception("Producto no encontrado.");
        }
        list2.setModel(dlm2);
    }
    public void llenarJlistCodigo(JList listaPorCodigo) {
        DefaultListModel dlm3 = new DefaultListModel();
        List<Productos> lista = papeleria.ordenarProductosPorCodigoBurbuja();
        for (Productos p : lista) {
            dlm3.addElement(p);
        }
        listaPorCodigo.setModel(dlm3);
    }
    public void llenasJlistPrecio(JList listaPorPrecio) {
        DefaultListModel dlm4 = new DefaultListModel();
        List<Productos> sortedList = papeleria.ordenarProductosPorPrecioInsercion();
        for (Productos p : sortedList) {
            dlm4.addElement(p);
        }
        listaPorPrecio.setModel(dlm4);
    }

    public void llenarJTextArea() {
        StringBuilder sb = new StringBuilder();
        for (Productos p : colaProductos.getCola()) {
            sb.append(p.toString()).append("\n\n");
        }
        textArea2.setText(sb.toString());
    }

    public void limpiarDatosPapeleria(){
        comboPapeleria.setSelectedIndex(0);
        textPapeleria1.setText("");
        textPapeleria2.setText("");
    }

    public void limpiarDatosFerreteria() {
        comboHerramientas.setSelectedIndex(0);
        textCodigoF.setText("");
        textPrecioF.setText("");
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public void limpiar() {
        txtCodigo.setText("");
        txtPrecio.setText("");
    }

    public void actualizarLista() {
        txtTexto.setText(pila.toString());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().Ventanita);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}

