package Producto;

import Producto.DAO.Producto;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class RowProducto implements Initializable {
    public Label codigo;
    public Label colocacion;
    public Label minima;
    public Label maxima;
    public Label stock;
    public Label proveedor;

    public Label estado;
    public Label nombre;

    public Label pMayorista;
    public Label pMayor;
    public Label pUnidad;


    public Button btnEliminar;
    public Button btnEditar;
    public HBox rootRow;
    public Button btnAgregar;
    private Producto productos;
    //El estilo de cada boton
    private final String estiloBoton="" +
            "-fx-background-color:#edebe9;" +
            "-fx-background-radius:100px;"+
            "-fx-margin:5px 0px 10px 0px;"

            ;

    //estilo de los estados
    private final String estadoActivo="" +
            "-fx-background-color:#54dea7;" +
            "-fx-text-fill:#ffffff;"+
            "-fx-font-weight: bold;"+
            "-fx-background-radius:10px;"+
            "-fx-margin:5px 0px 10px 0px;"

            ;    private final String estadoNoActivo="" +
            "-fx-background-color:#F03F37;" +
            "-fx-background-radius:10px;"+
            "-fx-font-weight: bold;"+
            "-fx-margin:5px 0px 10px 0px;"+
             "-fx-text-fill:#ffffff;"

            ;
//este son los metodos para llenar desde la clase lisCell
    public void setCodigo(int texto) {
        codigo.setText(String.valueOf(texto));
    }

    public void setColocacion(int texto) {
        colocacion.setText(String.valueOf(texto));
    }

    public void setMinima(int texto) {
        minima.setText(String.valueOf(texto));
    }

    public void setMaxima(int texto) {
        maxima.setText(String.valueOf(texto));
    }

    public void setStock(int texto) {
        stock.setText(String.valueOf(texto));
    }

    public void setProveedor(int texto) {
        proveedor.setText(String.valueOf(texto));
    }

    public void setNombre(String texto) {
        nombre.setText(texto);
    }
//para el esta si es activo  que aplicacio el estilo activo de lo contrario el no activo
    public void setEstado(String texto) {
        estado.setText(texto);
        estado.setPadding(new Insets(5));
        if (texto.equals("Activo")){
            estado.setStyle(estadoActivo);

        }else{
            estado.setStyle(estadoNoActivo);
        }

    }

    public void setpMayorista(Float texto) {
        pMayorista.setText(String.valueOf(texto));
    }

    public void setpMayor(Float texto) {
        pMayor.setText(String.valueOf(texto));
    }

    public void setpUnidad(Float texto) {
        pUnidad.setText(String.valueOf(texto));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

//esta funcion es para iniciar los botones y sus respectivos iconos
        initButton();

/*      btnEditar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println(codigo.getText().toString());
            }
        });*/

        btnAgregar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (estado.getText().equals("No Activo")){
                    System.out.println("Por favor Active el estado del producto para pode agregar al inventario");
                }else {
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
        });
    }

public void setAncho(Double ancho){rootRow.setPrefWidth(ancho);}

    public void pasar(Producto producto){
      if (producto!=null){
          productos=producto;
      }
    }
    //colocar icono, estilo y tamaño de los botones
    public void initButton(){
        btnEditar.setGraphic(icono("/img/edit.png"));
        btnEditar.setStyle(estiloBoton);
        btnEditar.setPrefHeight(35);
        btnEditar.setPrefWidth(20);
        btnEliminar.setGraphic(icono("/img/delete.png"));
        btnEliminar.setStyle(estiloBoton);
        btnEliminar.setPrefHeight(35);
        btnEliminar.setPrefWidth(20);
        btnAgregar.setGraphic(icono("/img/plus.png"));
        btnAgregar.setStyle(estiloBoton);
        btnAgregar.setPrefHeight(35);
        btnAgregar.setPrefWidth(20);
    }
    public static ImageView icono(String url){
        ImageView imageView = new ImageView(url);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        return imageView;
    }
}
