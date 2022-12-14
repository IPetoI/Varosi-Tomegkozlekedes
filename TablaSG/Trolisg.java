package TablaSG;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Trolisg {

    private final IntegerProperty Jármű_Azonosító = new SimpleIntegerProperty();
    private final IntegerProperty Mozgáskorlátozott_e = new SimpleIntegerProperty();



    public Integer getJármű_Azonosító() {
        return Jármű_Azonosító.get();
    }

    public void setJármű_Azonosító(int value) {
        Jármű_Azonosító.set(value);
    }

    public IntegerProperty Jármű_AzonosítóProperty() {
        return Jármű_Azonosító;
    }

    public Integer getMozgáskorlátozott_e() {
        return Mozgáskorlátozott_e.get();
    }

    public void setMozgáskorlátozott_e(int value) {
        Mozgáskorlátozott_e.set(value);
    }

    public IntegerProperty Mozgáskorlátozott_eProperty() {
        return Mozgáskorlátozott_e;
    }
}
