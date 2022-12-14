package TablaSG;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Jarmu_azonositoesmenetrendsg {

    private final IntegerProperty Jármű_Azonosító = new SimpleIntegerProperty();
    private final StringProperty Járatszám = new SimpleStringProperty();
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

    public String getJáratszám() {
        return Járatszám.get();
    }

    public void setJáratszám(String value) {
        Járatszám.set(value);
    }

    public StringProperty JáratszámProperty() {
        return Járatszám;
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
