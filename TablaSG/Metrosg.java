package TablaSG;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Metrosg {

    private final IntegerProperty Jármű_Azonosító = new SimpleIntegerProperty();
    private final StringProperty Szín = new SimpleStringProperty();


    public Integer getJármű_Azonosító() {
        return Jármű_Azonosító.get();
    }

    public void setJármű_Azonosító(int value) {
        Jármű_Azonosító.set(value);
    }

    public IntegerProperty Jármű_AzonosítóProperty() {
        return Jármű_Azonosító;
    }

    public String  getSzín() {
        return Szín.get();
    }

    public void setSzín(String value) {
        Szín.set(value);
    }

    public StringProperty SzínProperty() {
        return Szín;
    }
}
