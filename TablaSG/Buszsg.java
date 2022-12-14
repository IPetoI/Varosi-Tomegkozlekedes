package TablaSG;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Buszsg {

    private final IntegerProperty Jármű_Azonosító = new SimpleIntegerProperty();
    private final IntegerProperty Férőhely_szám = new SimpleIntegerProperty();


    public Integer getJármű_Azonosító() {
        return Jármű_Azonosító.get();
    }

    public void setJármű_Azonosító(int value) {
        Jármű_Azonosító.set(value);
    }

    public IntegerProperty Jármű_AzonosítóProperty() {
        return Jármű_Azonosító;
    }

    public Integer getFérőhely_szám() {
        return Férőhely_szám.get();
    }

    public void setFérőhely_szám(Integer value) {
        Férőhely_szám.set(value);
    }

    public IntegerProperty Férőhely_számProperty() {
        return Férőhely_szám;
    }
}
