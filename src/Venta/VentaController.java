package Venta;

import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class VentaController implements Initializable {
    public TextField txtFecha;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String fecha = String.valueOf(LocalDate.now());
        txtFecha.setText(fecha);
        txtFecha.setEditable(false);
        //numeroOrden(txtNoOrden);
    }
}
