package ClassAux;

import javafx.scene.control.TableView;

import java.util.concurrent.atomic.AtomicLong;

public class SizeColumnTable {
    public void  ajustarColumna(TableView<?> view){
        AtomicLong ancho= new AtomicLong();
        view.getColumns().forEach(tableColumn -> {
            ancho.addAndGet((long) tableColumn.getWidth());
        });
        double anchoTabla=view.getWidth();
        if (anchoTabla>ancho.get()){
            view.getColumns().forEach(tableColumn -> {
                tableColumn.setPrefWidth(tableColumn.getWidth()+((anchoTabla-ancho.get())/view.getColumns().size()));
            });
        }
    }
}
