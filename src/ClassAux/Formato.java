package ClassAux;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.beans.PropertyChangeEvent;
import java.util.function.UnaryOperator;

public class Formato {
    public Formato(){}

    public void decimal(TextField e) {
        e.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("\\d*(\\.\\d*)?")) {
                    e.setText(oldValue);
                }
            }
        });
        e.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                if (!newValue){
                    if (e.getText().matches("\\d+")){
                        e.setText(e.getText()+".00");
                    }
                }
            }
        });
    }

    public void  entero(TextField field, int tamanio) {
        UnaryOperator<TextFormatter.Change> enteros = new UnaryOperator<TextFormatter.Change>() {
            @Override
            public TextFormatter.Change apply(TextFormatter.Change change) {
                if (change.getText().matches("\\d+")) {
                    return change;
                } else {
                    change.setText("");
                    return change;
                }
            }
        };
        field.setTextFormatter(new TextFormatter<Object>(enteros));
        if (tamanio>0) {
            field.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                    if (field.getText().length() > tamanio) {
                        String texto = field.getText().substring(0, tamanio);
                        field.setText(texto);
                    }
                }
            });
        }
    }


}
