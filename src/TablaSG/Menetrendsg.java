package TablaSG;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Menetrendsg {

    private final IntegerProperty Jármű_Azonosító = new SimpleIntegerProperty();
    private final IntegerProperty Megálló_Azonosító = new SimpleIntegerProperty();
    private final StringProperty Beér = new SimpleStringProperty();


    public Integer getJármű_Azonosító() {
        return Jármű_Azonosító.get();
    }

    public void setJármű_Azonosító(int value) {
        Jármű_Azonosító.set(value);
    }

    public IntegerProperty Jármű_AzonosítóProperty() {
        return Jármű_Azonosító;
    }

    public Integer getMegálló_Azonosító() {
        return Megálló_Azonosító.get();
    }

    public void setMegálló_Azonosító(Integer value) {
        Megálló_Azonosító.set(value);
    }

    public IntegerProperty Megálló_AzonosítóProperty() {
        return Megálló_Azonosító;
    }

    public String getBeér() {
        return Beér.get();
    }

    public StringProperty beérProperty() {
        return Beér;
    }

    public void setBeér(String beér) {
        this.Beér.set(beér);
    }

}
