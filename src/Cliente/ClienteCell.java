package Cliente;

import ClassAux.AlertDialog;
import Cliente.DAO.Cliente;
import Cliente.DAO.DataCliente;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;

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
                   Cliente pro=new Cliente(Integer.parseInt(rowCliente.codigo.getText()),"x","x",0,0,"x");
                   DataCliente datos=new DataCliente();
                   datos.crudCliente(pro,"delete");
                   ClienteController clienteController=new ClienteController();
                   clienteController.initLista(getListView());
                   getListView().refresh();

               }
           }
       });

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


        setGraphic(graphic);
    }

}
