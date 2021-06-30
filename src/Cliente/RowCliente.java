package Cliente;

import Cliente.DAO.Cliente;
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

public class RowCliente implements Initializable {


    public Label codigo;
    public Label nombre;
    public Label sexo;
    public Label telefonouno;
    public Label telefonodos;
    public Label nit;




    public Button btnEliminar;
    public Button btnEditar;
    public HBox rootRow;
    public Button btnAgregar;
    private Cliente cliente;
    //El estilo de cada boton
    private final String estiloBoton="" +
            "-fx-background-color:#edebe9;" +
            "-fx-background-radius:100px;"+
            "-fx-margin:5px 0px 10px 0px;"
            ;

    //estilo de los estados
    private final String estadoActivo="" +
            "-fx-background-color:#00EB59;" +
            "-fx-text-fill:#ffffff;"+

            "-fx-background-radius:10px;"+
            "-fx-margin:5px 0px 10px 0px;"

            ;
    private final String estadoNoActivo="" +
            "-fx-background-color:#F03F37;" +
            "-fx-background-radius:10px;"+
            "-fx-margin:5px 0px 10px 0px;"+
            "-fx-text-fill:#ffffff;"

            ;
    //este son los metodos para llenar desde la clase lisCell
    public void setCodigo(int texto) {
        codigo.setText(String.valueOf(texto));
    }

    public void setTelefonouno(int texto) {telefonouno.setText(String.valueOf(texto));
    }

    public void setTelefonodos(int texto) {
        telefonodos.setText(String.valueOf(texto));
    }
    public void setSexo(String texto) {
        sexo.setText(texto);
    }
    public void setNombre(String texto) {
        nombre.setText(texto);
    }
    public void setNit(String texto){nit.setText(texto);}




    public void initialize(URL url, ResourceBundle resourceBundle) {

//esta funcion es para iniciar los botones y sus respectivos iconos
        initButton();






    }

    public void setAncho(Double ancho){rootRow.setPrefWidth(ancho);}


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
