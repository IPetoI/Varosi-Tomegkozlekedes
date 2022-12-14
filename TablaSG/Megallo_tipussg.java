package TablaSG;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Megallo_tipussg {

    private final IntegerProperty Megálló_Azonosító = new SimpleIntegerProperty();
    private final StringProperty Megálló_Típus = new SimpleStringProperty();


    public Integer getMegálló_Azonosító() {
        return Megálló_Azonosító.get();
    }

    public void setMegálló_Azonosító(int value) {
        Megálló_Azonosító.set(value);
    }

    public IntegerProperty Megálló_AzonosítóProperty() {
        return Megálló_Azonosító;
    }

    public String getMegálló_Típus() {
        return Megálló_Típus.get();
    }

    public void setMegálló_Típus(String value) {
        Megálló_Típus.set(value);
    }

    public StringProperty Megálló_TípusProperty() {
        return Megálló_Típus;
    }
}
