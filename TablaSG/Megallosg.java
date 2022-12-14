package TablaSG;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Megallosg {

    private final IntegerProperty Irányítószám = new SimpleIntegerProperty();
    private final IntegerProperty Megálló_Azonosító = new SimpleIntegerProperty();


    public Integer getIrányítószám() {
        return Irányítószám.get();
    }

    public void setIrányítószám(int value) {
        Irányítószám.set(value);
    }

    public IntegerProperty IrányítószámProperty() {
        return Irányítószám;
    }

    public Integer getMegálló_Azonosító() {
        return Megálló_Azonosító.get();
    }

    public void setMegálló_Azonosító(int value) {
        Megálló_Azonosító.set(value);
    }

    public IntegerProperty Megálló_AzonosítóProperty() {
        return Megálló_Azonosító;
    }
}
