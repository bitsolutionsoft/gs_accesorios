package Cliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClienteController {
    public void nuevoCliente(ActionEvent actionEvent) {
        try {
            Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Cliente/FormCLiente.fxml"));
            Stage stage=new Stage();
            stage.setScene(new Scene(parent));
            stage.show();


        }catch (IOException e){
            e.printStackTrace();

        }
    }

}
