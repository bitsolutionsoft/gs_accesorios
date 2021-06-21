package Menu;

import Conexion.Conexion;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Menu implements Initializable {

    public BorderPane Menu;
    public VBox PanelMenu;
    public Button btnProducto;
    public Button btnCliente;
    public Button btnProveedor;
    public Button btnVenta;
    public Button btnColocacion;
    public Button btnInforme;
    private String anterior="Producto";

    private final String estiloBoton="" +
            "-fx-background-color:#325FD4;" +
            "-fx-text-fill:#ffffff;" +
            "-fx-font-size:20px;"
            ;
    private final String selectBoton="" +
            "-fx-background-color:#0EC3DC;" +
            "-fx-text-fill:#ffffff;" +
            "-fx-font-size:20px;" +
            "-fx-background-radius:10px;" +
            "-fx-margin:10px 0px 10px 0px;"
            ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
   iniciarBotones();
        VBox panelCenter=(VBox) vista("/Venta/Venta.fxml");
        panelCenter.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        panelCenter.setPadding(new Insets(10,10,10,10));
        Menu.setCenter(panelCenter);
        cambiarColor(btnVenta);
        anterior="PuntoVenta";
    }

public  void iniciarBotones(){

                btnProducto.setGraphic(icono("/img/producto.png"));
                btnProducto.setStyle(estiloBoton);


                btnCliente.setGraphic(icono("/img/cliente.png"));
                btnCliente.setStyle(estiloBoton);

                btnProveedor.setGraphic(icono("/img/proveedor.png"));
                btnProveedor.setStyle(estiloBoton);

                btnVenta.setGraphic(icono("/img/puntoventa.png"));
                btnVenta.setStyle(estiloBoton);

                btnColocacion.setGraphic(icono("/img/colocacion.png"));
                btnColocacion.setStyle(estiloBoton);

                btnInforme.setGraphic(icono("/img/informe.png"));
                btnInforme.setStyle(estiloBoton);

}
public static ImageView icono(String url){
        ImageView imageView = new ImageView(url);
        imageView.setFitHeight(25);
        imageView.setFitWidth(25);

        return imageView;
}

    public void AbrirVenta(ActionEvent actionEvent) {
        VBox panelCenter=(VBox) vista("/Venta/Venta.fxml");
        panelCenter.setMaxSize(Double.MAX_EXPONENT,Double.MAX_EXPONENT);

        panelCenter.setPadding(new Insets(10,10,10,10));
        Menu.setCenter(panelCenter);
        cambiarColor(btnVenta);
        anterior="PuntoVenta";


    }
    public void AbrirProducto(ActionEvent actionEvent) {
      VBox panelCenter=(VBox) vista("/Producto/Producto.fxml");
      panelCenter.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
      panelCenter.setPadding(new Insets(10,10,10,10));
      Menu.setCenter(panelCenter);
      cambiarColor(btnProducto);
      anterior="Producto";


    }
    public void AbrirCliente(ActionEvent actionEvent) {
        VBox panelCenter=(VBox) vista("/Cliente/Cliente.fxml");
        panelCenter.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        panelCenter.setPadding(new Insets(10));
        Menu.setCenter(panelCenter);
        cambiarColor(btnCliente);
        anterior="Cliente";
    }
    public void AbrirProveedor(ActionEvent actionEvent) {
        VBox panelCenter=(VBox) vista("/Proveedor/Proveedor.fxml");
        panelCenter.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        panelCenter.setPadding(new Insets(10));
        Menu.setCenter(panelCenter);
        cambiarColor(btnProveedor);
        anterior="Proveedor";
    }


    public void AbrirColocacion(ActionEvent actionEvent) {
        VBox panelCenter=(VBox) vista("/Colocacion/Colocacion.fxml");
        panelCenter.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        panelCenter.setPadding(new Insets(10));
        Menu.setCenter(panelCenter);
        cambiarColor(btnColocacion);
        anterior="Colocacion";
    }
    public void AbrirInforme(ActionEvent actionEvent) {
        VBox panelCenter=(VBox) vista("/Informe/Informe.fxml");
        panelCenter.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        panelCenter.setPadding(new Insets(10));
        Menu.setCenter(panelCenter);
        cambiarColor(btnInforme);
        anterior="Informe";
    }
    public  void  cambiarColor(Button bntActual){
        switch (anterior) {
            case "PuntoVenta":
                btnVenta.setStyle(estiloBoton);
                btnVenta.setGraphic(icono("/img/puntoventa.png"));
                break;
            case "Producto":
                btnProducto.setStyle(estiloBoton);
                btnProducto.setGraphic(icono("/img/producto.png"));
                break;
            case "Cliente":
                btnCliente.setStyle(estiloBoton);
                btnCliente.setGraphic(icono("/img/cliente.png"));
                break;
            case "Proveedor":
                btnProveedor.setStyle(estiloBoton);
                btnProveedor.setGraphic(icono("/img/proveedor.png"));
                break;
            case "Colocacion":
                btnColocacion.setStyle(estiloBoton);
                btnColocacion.setGraphic(icono("/img/colocacion.png"));
                break;

            case "Informe":
                btnInforme.setStyle(estiloBoton);
                btnInforme.setGraphic(icono("/img/informe.png"));
                break;
        }
        bntActual.setGraphic(icono("/img/check.png"));
        bntActual.setStyle(selectBoton);

    }

    public Parent vista(String url){

        try {
            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource(url));
            Parent   root = fxmlLoader.load();
            return root;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new VBox();

    }


}
