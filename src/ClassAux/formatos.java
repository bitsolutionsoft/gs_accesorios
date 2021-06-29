package ClassAux;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

public class formatos   {
    public formatos(){}

public void entero(TextField field) {
    UnaryOperator<TextFormatter.Change> enteros = new UnaryOperator<TextFormatter.Change>() {
        @Override
        public TextFormatter.Change apply(TextFormatter.Change change) {
            if (change.getText().matches("\\d+")) {
                return change; //if change is a number
            } else {
                change.setText(""); //else make no change
               /* change.setRange(    //don't remove any selected text either.
                        change.getRangeStart(),
                        change.getRangeStart()
                );*/
                return change;
            }
        }
    };
    field.setTextFormatter(new TextFormatter<Object>(enteros));
}

    public void decimal(TextField field) {
        UnaryOperator<TextFormatter.Change> decimal = new UnaryOperator<TextFormatter.Change>() {
            @Override
            public TextFormatter.Change apply(TextFormatter.Change change) {
                if (change.getText().matches("\\d+\\.\\d+")) {
                    return change; //if change is a number
                } else {
                    change.setText(""); //else make no change
               /* change.setRange(    //don't remove any selected text either.
                        change.getRangeStart(),
                        change.getRangeStart()
                );*/
                    return change;
                }
            }
        };
        field.setTextFormatter(new TextFormatter<Float>(decimal));
    }
}
