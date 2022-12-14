package TablaSG;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Jarmusg {

    private final IntegerProperty Irányítószám = new SimpleIntegerProperty();
    private final IntegerProperty Jármű_Azonosító = new SimpleIntegerProperty();


    public Integer getIrányítószám() {
        return Irányítószám.get();
    }

    public void setIrányítószám(int value) {
        Irányítószám.set(value);
    }

    public IntegerProperty IrányítószámProperty() {
        return Irányítószám;
    }

    public Integer getJármű_Azonosító() {
        return Jármű_Azonosító.get();
    }

    public void setJármű_Azonosító(int value) {
        Jármű_Azonosító.set(value);
    }

    public IntegerProperty Jármű_AzonosítóProperty() {
        return Jármű_Azonosító;
    }
}
