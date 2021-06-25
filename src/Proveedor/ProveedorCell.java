package Proveedor;

import ClassAux.AlertDialog;
import Cliente.DAO.Cliente;
import Cliente.DAO.DataCliente;
import Cliente.RowCliente;
import Proveedor.DAO.DataProveedor;
import Proveedor.DAO.Proveedor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class ProveedorCell extends ListCell<Proveedor> {
    //declaramos un nodo y un objeto de rowproducto
    AlertDialog alertDialog=new AlertDialog();
    private Node graphic;
    private RowProveedor rowProveedor;
    // el constructor donde llamamos el el rowproducto
    public ProveedorCell(){
        FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().getResource("Proveedor/RowProveedor.fxml"));
        try {
            graphic=loader.load();
        }catch (IOException e){
            e.printStackTrace();
        }
        rowProveedor=loader.getController();
        //el evento del botono eliminar para eliminar productos
        rowProveedor.btnEliminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (alertDialog.alertConfirm("Proveedor", "esta seguro de elliminar al proveedor")){
                    Proveedor pro=new Proveedor(Integer.parseInt(rowProveedor.codigo.getText()),"x","x",0,0,"x","x","x","x");
                    DataProveedor datos=new DataProveedor();
                    datos.crudProveedor(pro,"delete");
                    ProveedorController proveedorController=new ProveedorController();
                    proveedorController.initLista(getListView());
                    getListView().refresh();

                }
            }
        });

    }
    //aqui llenas la lista con  el rowProducto
    @Override
    protected void updateItem(Proveedor proveedor, boolean empty){
        super.updateItem(proveedor, empty);
        if (empty){
            clearContent();
        }else{
            addContent(proveedor);
            //  rowProducto.pasar(getItem());
        }
    }
    //para limpiar contenido
    private void clearContent(){
        setGraphic(null);
    }
    //agregamos contenido a cada label creado en el rowproducto
    private void addContent(Proveedor proveedor){
        setText(null);
        //  rowProducto.setAncho(getListView().getWidth()-16);
        rowProveedor.setCodigo(proveedor.getIdProveedor());
        rowProveedor.setNombre(proveedor.getNombre()+"  "+proveedor.getApellido());
        rowProveedor.setTelefonouno(proveedor.getTelefonoUno());
        rowProveedor.setTelefonodos(proveedor.getTelefonoDos());

        rowProveedor.setSexo(proveedor.getSexo());
        rowProveedor.setCompania(proveedor.getCompania());
        rowProveedor.setDireccion(proveedor.getDireccion());
        rowProveedor.setEstado(proveedor.getEstado());

        setGraphic(graphic);
    }

}
