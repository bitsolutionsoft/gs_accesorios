package Colocacion;

import ClassAux.AlertDialog;

import Colocacion.DAO.Colocacion;
import Colocacion.DAO.DataColocacion;
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

public class ColocacionCell extends ListCell<Colocacion> {

    //declaramos un nodo y un objeto de rowproducto
    AlertDialog alertDialog=new AlertDialog();
    private Node graphic;
    private RowColocacion rowColocacion;
    // el constructor donde llamamos el el rowproducto
    public ColocacionCell(){
        FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().getResource("Colocacion/RowColocacion.fxml"));
        try {
            graphic=loader.load();
        }catch (IOException e){
            e.printStackTrace();
        }
        rowColocacion=loader.getController();
        //el evento del botono eliminar para eliminar productos
        rowColocacion.btnEliminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (alertDialog.alertConfirm("Colocacion", "esta seguro de elliminar la colocación")){
                    Colocacion pro=new Colocacion(Integer.parseInt(rowColocacion.codigo.getText()),"x","x");
                    DataColocacion datos=new DataColocacion();
                    datos.crudColocacion(pro,"delete");
                    ColocacionController colocacionController=new ColocacionController();
                    colocacionController.initLista(getListView());
                    getListView().refresh();

                }
            }
        });
        rowColocacion.btnEditar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                Colocacion colocacion = new Colocacion();
                for (int i = 0; i < getListView().getItems().size(); i++) {
                    if (Integer.parseInt(rowColocacion.codigo.getText()) == getListView().getItems().get(i).getIdColocacion()) {
                        colocacion.setIdColocacion(getListView().getItems().get(i).getIdColocacion());
                        colocacion.setNombre(getListView().getItems().get(i).getNombre());
                        colocacion.setEstado(getListView().getItems().get(i).getEstado());


                    }
                }
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Colocacion/FormColocacion.fxml"));
                    Parent parent = loader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Modificar colocacion");
                    stage.getIcons().add(new Image("/img/icon.png"));
                    stage.setScene(new Scene(parent));
                    FormColocacion formColocacion = loader.<FormColocacion>getController();
                    formColocacion.pasarRegistro(colocacion);
                    stage.show();
                    stage.setOnHiding((event -> {
                        ColocacionController colocacionController = new ColocacionController();
                        colocacionController.initLista(getListView());
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
    protected void updateItem(Colocacion colocacion, boolean empty){
        super.updateItem(colocacion, empty);
        if (empty){
            clearContent();
        }else{
            addContent(colocacion);
            //  rowProducto.pasar(getItem());
        }
    }
    //para limpiar contenido
    private void clearContent(){
        setGraphic(null);
    }
    //agregamos contenido a cada label creado en el rowproducto
    private void addContent(Colocacion colocacion){
        setText(null);
        //  rowProducto.setAncho(getListView().getWidth()-16);
        rowColocacion.setCodigo(colocacion.getIdColocacion());
        rowColocacion.setNombre(colocacion.getNombre());
        rowColocacion.setEstado(colocacion.getEstado());


        setGraphic(graphic);
    }
}
