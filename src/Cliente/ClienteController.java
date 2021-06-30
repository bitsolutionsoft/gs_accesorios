package Cliente;

import Cliente.DAO.Cliente;
import Cliente.DAO.DataCliente;

import Producto.DAO.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClienteController implements Initializable {

    public ListView<Cliente> listCliente;
    public Button btnNuevo;
    public TextField txtBuscar;


    String hcambio="";
    RowCliente rowCliente=new RowCliente();

    static    ObservableList<Cliente> clientes ;
    static    FilteredList<Cliente> clientedata;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initLista(listCliente);
        llenarListaCliente();



    }

    //inixcair las listas para el ListView y combobox
    public void initLista(ListView<Cliente> listView){
        DataCliente datos=new DataCliente();
        clientes = FXCollections.observableArrayList(datos.viewCliente("viewall"));
        clientedata=new FilteredList<Cliente>(clientes,s->true);
        listView.setItems(clientedata);
        //para llenar las filas personalizadas

        listView.setCellFactory(new Callback<ListView<Cliente>, ListCell<Cliente>>() {
            @Override
            public ListCell<Cliente> call(ListView<Cliente> param) {
                ClienteCell clienteCell=new ClienteCell();
                return clienteCell;
            }
        });

    }

    //combobox ordenar por


//llenarel list View

    public void llenarListaCliente(){

        //capturar el texto y filtrar
        txtBuscar.textProperty().addListener((prop,old,text) ->{
            clientedata.setPredicate(cliente ->{
                if (text==null || text.isEmpty()){
                    return  true;
                }
                String texto=text.toLowerCase();
                if(String.valueOf(cliente.getCodigo()).toLowerCase().contains(texto)){
                    return true;
                }
                else if(cliente.getNombre().toLowerCase().contains(texto)){
                    return true;
                }
                else if(cliente.getApellido().toLowerCase().contains(texto)){
                    return true;
                }

                return false;
            });
        });



    }


    public void nuevoCliente(ActionEvent actionEvent) {

        try {
            Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Cliente/FormCLiente.fxml"));
            Stage stage=new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
            stage.getIcons().add(new Image("/img/icon.png"));
            stage.setOnHiding((event ->{
                initLista(listCliente);
                llenarListaCliente();
                listCliente.refresh();
            }));

        }catch (IOException e){
            e.printStackTrace();

        }
    }

}
