package Producto;

import Producto.DAO.Data;
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
    public ComboBox<String> cbCategoria;
    public ComboBox <String>  cbOrdenar;
    Data datos=new Data();
    String hcambio="";
    RowProducto rowProducto=new RowProducto();

    ObservableList<Producto> productos ;
    FilteredList<Producto> proData;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initLista();
        llenarListaProducto();
        llenarCategoria();
        llenarOrdenarPor();

//evento para detectar si hay cambios en la lista
productos.addListener(new ListChangeListener<Producto>() {
    @Override
    public void onChanged(Change<? extends Producto> change) {
      initLista();
      llenarListaProducto();
    }
});

    }

//inixcair las listas para el ListView y combobox
    public void initLista(){
        productos =FXCollections.observableArrayList(datos.viewProducto("viewall"));
        proData=new FilteredList<Producto>(productos,s->true);
    }

    //combobox ordenar por

    public void llenarOrdenarPor(){
        ObservableList<String> modelo= FXCollections.observableArrayList();
        for (int i=0; i<productos.size();i++){
            ArrayList<String> item=new ArrayList<>();
            item.add(productos.get(i).getModelo());
            modelo.addAll(item);
        }

        cbOrdenar.setPromptText("Seleccione el modelo");
        cbOrdenar.setItems(modelo);
        cbOrdenar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String newText=cbOrdenar.getSelectionModel().getSelectedItem().toLowerCase();

                proData.setPredicate(producto ->{
                    if (newText==null  || newText.isEmpty()){
                        return  true;
                    }
                    String texto=newText.toLowerCase();
                    if(producto.getModelo().toLowerCase().contains(texto)){
                        return true;
                    }

                    return false;
                });
            }
        });

    }
// combobox categoria
    public void llenarCategoria(){
        ObservableList<String> nombre= FXCollections.observableArrayList();
        for (int i=0; i<productos.size();i++) {
            ArrayList<String> item = new ArrayList<>();
            item.add(productos.get(i).getNombre());
            nombre.addAll(item);
        }
            cbCategoria.setPromptText("Seleccione el nombre");
            cbCategoria.setItems(nombre);

            cbCategoria.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    String newText=cbCategoria.getSelectionModel().getSelectedItem().toLowerCase();

                    proData.setPredicate(producto ->{
                        if (newText==null  || newText.isEmpty()){
                            return  true;
                        }
                        String texto=newText.toLowerCase();
                        if(producto.getNombre().toLowerCase().contains(texto)){
                            return true;
                        }

                        return false;
                    });
                }
            });

        }



//llenarel list View

    public void llenarListaProducto(){
//para mandar la list al listview
        listProducto.setItems(proData);
        //para llenar las filas personalizadas

        listProducto.setCellFactory(new Callback<ListView<Producto>, ListCell<Producto>>() {
            @Override
            public ListCell<Producto> call(ListView<Producto> param) {
                ProCell proCell=new ProCell();
                return proCell;
            }
        });
        //capturar el texto y filtrar
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
    public   void refreshList(Producto pro){
if (pro!=null){
    Data data=new Data();
    data.crudProducto(pro,"delete");
            initLista();
         llenarListaProducto();
}


    }

    public void nuevoProducto(ActionEvent actionEvent) {
        try {
            Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Producto/FormProducto.fxml"));
            Stage stage=new Stage();
            stage.setScene(new Scene(parent));
            stage.show();

        }catch (IOException e){
            e.printStackTrace();

    }
    }
}
