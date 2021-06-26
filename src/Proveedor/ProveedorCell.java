package Proveedor;

import ClassAux.AlertDialog;
import ClassAux.Util;
import Cliente.DAO.Cliente;
import Cliente.DAO.DataCliente;
import Cliente.RowCliente;
import Producto.DAO.Producto;
import Proveedor.DAO.DataProveedor;
import Proveedor.DAO.Proveedor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.stage.Stage;

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
        //aquii dejas los eventos del boton donde los tenes te dar problema por el costructor
        //evento del boton modificar _______________________________________________________________________________________//

        rowProveedor.btnEditar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (rowProveedor.estado.getText().equals("No Activo")) {
                    Util.Error("Producto", "Por favor Active  el producto para poder modificar");

                } else {
                    Proveedor proveedor = new Proveedor();
                    for (int i = 0; i < getListView().getItems().size(); i++) {
                        if (Integer.parseInt(rowProveedor.codigo.getText()) == getListView().getItems().get(i).getIdProveedor()) {
                            proveedor.setIdProveedor(getListView().getItems().get(i).getIdProveedor());
                            proveedor.setNombre(getListView().getItems().get(i).getNombre());
                            proveedor.setApellido(getListView().getItems().get(i).getApellido());
                            proveedor.setTelefonoUno(getListView().getItems().get(i).getTelefonoUno());
                            proveedor.setTelefonoDos(getListView().getItems().get(i).getTelefonoDos());
                            proveedor.setCompania(getListView().getItems().get(i).getCompania());
                            proveedor.setDireccion(getListView().getItems().get(i).getDireccion());
                            proveedor.setSexo(getListView().getItems().get(i).getSexo());
                            proveedor.setEstado(getListView().getItems().get(i).getEstado());


                        }
                    }
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Proveedor/FormProveedor.fxml"));
                        Parent parent = loader.load();
                        Stage stage = new Stage();
                        stage.setTitle("Modificar producto");
                        stage.getIcons().add(new Image("/img/icon.png"));
                        stage.setScene(new Scene(parent));
                        FormProveedor formProveedor = loader.<FormProveedor>getController();
                        formProveedor.pasarRegistro(proveedor);
                        stage.show();
                        stage.setOnHiding((event -> {
                            ProveedorController proveedorController = new ProveedorController();
                            proveedorController.initLista(getListView());
                            getListView().refresh();
                        }));

                    } catch (IOException e) {
                        e.printStackTrace();

                    }
                }
            }
        });
        //final del evento modificar


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
