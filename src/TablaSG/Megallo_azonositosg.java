package TablaSG;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Megallo_azonositosg {

    private final IntegerProperty Megálló_Azonosító = new SimpleIntegerProperty();
    private final StringProperty Megálló_Név = new SimpleStringProperty();


    public Integer getMegálló_Azonosító() {
        return Megálló_Azonosító.get();
    }

    public void setMegálló_Azonosító(int value) {
        Megálló_Azonosító.set(value);
    }

    public IntegerProperty Megálló_AzonosítóProperty() {
        return Megálló_Azonosító;
    }

    public String getMegálló_Név() {
        return Megálló_Név.get();
    }

    public void setMegálló_Név(String value) {
        Megálló_Név.set(value);
    }

    public StringProperty Megálló_NévProperty() {
        return Megálló_Név;
    }
}
