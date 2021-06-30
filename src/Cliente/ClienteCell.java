package Cliente;

import ClassAux.AlertDialog;
import Cliente.DAO.Cliente;
import Cliente.DAO.DataCliente;

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

public class ClienteCell extends ListCell<Cliente> {
    //declaramos un nodo y un objeto de rowproducto
    AlertDialog alertDialog=new AlertDialog();
    private Node graphic;
    private RowCliente rowCliente;
    // el constructor donde llamamos el el rowproducto
    public ClienteCell(){
        FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().getResource("Cliente/RowCliente.fxml"));
        try {
            graphic=loader.load();
        }catch (IOException e){
            e.printStackTrace();
        }
        rowCliente=loader.getController();
        //el evento del botono eliminar para eliminar productos
       rowCliente.btnEliminar.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent actionEvent) {
               if (alertDialog.alertConfirm("Cliente", "esta seguro de elliminar el cliente")){
                   Cliente pro=new Cliente(Integer.parseInt(rowCliente.codigo.getText()),"x","x",0,0,"x","x");
                   DataCliente datos=new DataCliente();
                   datos.crudCliente(pro,"delete");
                   ClienteController clienteController=new ClienteController();
                   clienteController.initLista(getListView());
                   getListView().refresh();

               }
           }
       });
        rowCliente.btnEditar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                Cliente cliente = new Cliente();
                for (int i = 0; i < getListView().getItems().size(); i++) {
                    if (Integer.parseInt(rowCliente.codigo.getText()) == getListView().getItems().get(i).getCodigo()) {
                        cliente.setCodigo(getListView().getItems().get(i).getCodigo());
                        cliente.setNombre(getListView().getItems().get(i).getNombre());
                        cliente.setApellido(getListView().getItems().get(i).getApellido());
                        cliente.setTelefonoUno(getListView().getItems().get(i).getTelefonoUno());
                        cliente.setTelefonoDos(getListView().getItems().get(i).getTelefonoDos());
                        cliente.setNit(getListView().getItems().get(i).getNit());
                        cliente.setSexo(getListView().getItems().get(i).getSexo());


                    }
                }
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Cliente/FormCliente.fxml"));
                    Parent parent = loader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Modificar cliente");
                    stage.getIcons().add(new Image("/img/icon.png"));
                    stage.setScene(new Scene(parent));
                    FormCliente formCliente = loader.<FormCliente>getController();
                    formCliente.pasarRegistro(cliente);
                    stage.show();
                    stage.setOnHiding((event -> {
                        ClienteController clienteController = new ClienteController();
                        clienteController.initLista(getListView());
                        getListView().refresh();
                    }));

                } catch (IOException e) {
                    e.printStackTrace();

                }
            }

        });
        //final del evento modificar

    }
    //aqui llenas la lista con  el rowProducto
    @Override
    protected void updateItem(Cliente cliente, boolean empty){
        super.updateItem(cliente, empty);
        if (empty){
            clearContent();
        }else{
            addContent(cliente);
            //  rowProducto.pasar(getItem());
        }
    }
    //para limpiar contenido
    private void clearContent(){
        setGraphic(null);
    }
    //agregamos contenido a cada label creado en el rowproducto
    private void addContent(Cliente cliente){
        setText(null);
        //  rowProducto.setAncho(getListView().getWidth()-16);
        rowCliente.setCodigo(cliente.getCodigo());
        rowCliente.setNombre(cliente.getNombre()+"  "+cliente.getApellido());
        rowCliente.setTelefonouno(cliente.getTelefonoUno());
        rowCliente.setTelefonodos(cliente.getTelefonoDos());
        rowCliente.setSexo(cliente.getSexo());
        rowCliente.setNit(cliente.getNit());

        setGraphic(graphic);
    }

}
