package Proveedor;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ProveedorController {

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
