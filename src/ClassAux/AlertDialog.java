package ClassAux;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Optional;

public class AlertDialog {

    public  Boolean alertConfirm(String titulo, String mensaje){
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("gsaccesorios");
        alert.setHeaderText(titulo);
        alert.setContentText(mensaje);
        alert.setGraphic(new ImageView("/img/question.png"));
        DialogPane dialogPane=alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/Style/styleAlert.css").toExternalForm());
        Stage stage=(Stage)alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/img/icon.png"));
        Optional<ButtonType> result=alert.showAndWait();

        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            return true;
        }else {
        return false;
        }
    }

}
