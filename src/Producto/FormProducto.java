package Producto;

import Producto.DAO.Producto;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class FormProducto implements Initializable {
    private String accion="new";
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void pasarRegistro(Producto producto){
        if (producto !=null){
            accion="update";

        }
    }
}
