package TablaSG;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Villamossg {

    private final IntegerProperty Kocsi_szám = new SimpleIntegerProperty();
    private final IntegerProperty Jármű_Azonosító = new SimpleIntegerProperty();


    public Integer getKocsi_szám() {
        return Kocsi_szám.get();
    }

    public void setKocsi_szám(int value) {
        Kocsi_szám.set(value);
    }

    public IntegerProperty Kocsi_számProperty() {
        return Kocsi_szám;
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
