package TablaSG;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Iranyitoszamesjarmusg {

    private final IntegerProperty Irányítószám = new SimpleIntegerProperty();
    private final StringProperty Város_Név = new SimpleStringProperty();
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

    public String getVáros_Név() {
        return Város_Név.get();
    }

    public void setVáros_Név(String value) {
        Város_Név.set(value);
    }

    public StringProperty Város_NévProperty() {
        return Város_Név;
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
