package Producto;

import Producto.DAO.DataProducto;
import Producto.DAO.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProductoController implements Initializable {
    public ListView<Producto> listProducto;
    public Button btnNuevo;
    public TextField txtBuscar;





  static    ObservableList<Producto> productos ;
  static    FilteredList<Producto> proData;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initLista(listProducto);
        llenarListaProducto();




    }

//inixcair las listas para el ListView y combobox
    public void initLista(ListView<Producto> listview){
        DataProducto datos=new DataProducto();
        productos =FXCollections.observableArrayList(datos.viewProducto("viewall"));
        proData=new FilteredList<Producto>(productos,s->true);
        listview.setItems(proData);
        //para llenar las filas personalizadas

        listview.setCellFactory(new Callback<ListView<Producto>, ListCell<Producto>>() {
            @Override
            public ListCell<Producto> call(ListView<Producto> param) {
                ProCell proCell=new ProCell();
                return proCell;
            }
        });

    }

    //combobox ordenar por


// combobox categoria


//llenarel list View

    public void llenarListaProducto(){

        txtBuscar.textProperty().addListener((prop,old,text) ->{
            proData.setPredicate(producto ->{
                if (text==null || text.isEmpty()){
                    return  true;
                }
                String texto=text.toLowerCase();
                if(String.valueOf(producto.getCodigo()).toLowerCase().contains(texto)){
                    return true;
                }
                else if(producto.getNombre().toLowerCase().contains(texto)){
                    return true;
                }
                else if(producto.getModelo().toLowerCase().contains(texto)){
                    return true;
                }
                else if(producto.getEspecificacion().toLowerCase().contains(texto)){
                    return true;
                }
                return false;
            });
        });


    }

    public void nuevoProducto(ActionEvent actionEvent) {
        try {
            Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Producto/FormProducto.fxml"));
            Stage stage=new Stage();
            stage.setScene(new Scene(parent));
            stage.  show();
            stage.setTitle("Ingresar nuevo producto");
            stage.getIcons().add(new Image("/img/icon.png"));
            stage.setOnHiding((event ->{
                initLista(listProducto);
               // llenarListaProducto();
                listProducto.refresh();
            }));

        }catch (IOException e){
            e.printStackTrace();

    }
    }
}
