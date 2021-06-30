package Colocacion;


import Colocacion.DAO.Colocacion;
import Colocacion.DAO.DataColocacion;
import Producto.DAO.DataProducto;
import Producto.DAO.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ColocacionController implements Initializable {
    public Button btnIngresarNuevo;
    public TextField txtBuscar;


    public ListView<Colocacion> listColocacion;
    public ListView<Producto>listProducto;


    String hcambio="";
    RowColocacion rowColocacion=new RowColocacion();

    static ObservableList<Colocacion> colocaciones ;
    static FilteredList<Colocacion> colocaciondata;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initLista(listColocacion);
        llenarListaColocacion();
        listColocacion.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override

            public void handle(MouseEvent mouseEvent) {
                int codigo = listColocacion.getSelectionModel().getSelectedItem().getIdColocacion();
                Producto producto = new Producto(0, "", "", "", 0, 0, 0, 0, 0, 0, 0, codigo, 0, "", 0, 0, "", "");
                DataProducto dataProducto=new DataProducto();
                ObservableList <Producto> lista=FXCollections.observableArrayList(dataProducto.viewProductoXCol(producto,"viewxcol"));
                listProducto.setItems(lista);
            }
        });



    }

    //inixcair las listas para el ListView y combobox
    public void initLista(ListView<Colocacion> listView){
        DataColocacion datos=new DataColocacion();
        colocaciones = FXCollections.observableArrayList(datos.viewColocacion("viewall"));
        colocaciondata=new FilteredList<Colocacion>(colocaciones, s->true);
        listView.setItems(colocaciondata);
        //para llenar las filas personalizadas

        listView.setCellFactory(new Callback<ListView<Colocacion>, ListCell<Colocacion>>() {
            @Override
            public ListCell<Colocacion> call(ListView<Colocacion> param) {
                ColocacionCell colocacionCell=new ColocacionCell();
                return colocacionCell;
            }
        });

    }

    //combobox ordenar por


//llenarel list View

    public void llenarListaColocacion(){

        //capturar el texto y filtrar
        txtBuscar.textProperty().addListener((prop,old,text) ->{
            colocaciondata.setPredicate(colocacion ->{
                if (text==null || text.isEmpty()){
                    return  true;
                }
                String texto=text.toLowerCase();
                if(String.valueOf(colocacion.getIdColocacion()).toLowerCase().contains(texto)){
                    return true;
                }
                else if(colocacion.getNombre().toLowerCase().contains(texto)){
                    return true;
                }
                else if(colocacion.getEstado().toLowerCase().contains(texto)){
                    return true;
                }

                return false;
            });
        });



    }


    public void nuevaColocacion(ActionEvent actionEvent) {
        try {
            Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Colocacion/FormColocacion.fxml"));
            Stage stage=new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
            stage.setOnHiding((event ->{

             llenarListaColocacion();
             initLista(listColocacion);
            }));

        }catch (IOException e){
            e.printStackTrace();

        }
    }
}
