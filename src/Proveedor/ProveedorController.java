package Proveedor;

import Cliente.DAO.Cliente;
import Cliente.DAO.DataCliente;
import Proveedor.DAO.DataProveedor;
import Proveedor.DAO.Proveedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProveedorController implements Initializable  {
    public ListView<Proveedor> listProveedor;
    public Button btnNuevo;
    public TextField txtBuscar;


    String hcambio="";
    RowProveedor rowCliente=new RowProveedor();

    static    ObservableList<Proveedor> proveedores;
    static    FilteredList<Proveedor> proveedordata;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initLista(listProveedor);
        llenarListaProveedor();



    }

    //inixcair las listas para el ListView y combobox
    public void initLista(ListView<Proveedor> listView){
        DataProveedor datos=new DataProveedor();
        proveedores = FXCollections.observableArrayList(datos.viewProveedor("viewall"));
        proveedordata=new FilteredList<Proveedor>(proveedores,s->true);
        listView.setItems(proveedordata);
        //para llenar las filas personalizadas

        listView.setCellFactory(new Callback<ListView<Proveedor>, ListCell<Proveedor>>() {
            @Override
            public ListCell<Proveedor> call(ListView<Proveedor> param) {
                ProveedorCell proveedorCell=new ProveedorCell();
                return proveedorCell;
            }
        });

    }

    //combobox ordenar por


//llenarel list View

    public void llenarListaProveedor(){

        //capturar el texto y filtrar
        txtBuscar.textProperty().addListener((prop,old,text) ->{
            proveedordata.setPredicate(proveedor ->{
                if (text==null || text.isEmpty()){
                    return  true;
                }
                String texto=text.toLowerCase();
                if(String.valueOf(proveedor.getIdProveedor()).toLowerCase().contains(texto)){
                    return true;
                }
                else if(proveedor.getNombre().toLowerCase().contains(texto)){
                    return true;
                }
                else if(proveedor.getApellido().toLowerCase().contains(texto)){
                    return true;
                }

                return false;
            });
        });



    }


    public void nuevoProveedor(ActionEvent actionEvent) {

        try {
            Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Proveedor/FormProveedor.fxml"));
            Stage stage=new Stage();
            stage.setScene(new Scene(parent));
            stage.show();


        }catch (IOException e){
            e.printStackTrace();

        }
    }

    }
