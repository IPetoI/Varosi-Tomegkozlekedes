package TablaSG;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Iranyitoszamesjegysg {

    private final IntegerProperty Irányítószám = new SimpleIntegerProperty();
    private final StringProperty Város_Név = new SimpleStringProperty();
    private final IntegerProperty Diák_ár = new SimpleIntegerProperty();


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

    public Integer getDiák_ár() {
        return Diák_ár.get();
    }

    public IntegerProperty diák_árProperty() {
        return Diák_ár;
    }

    public void setDiák_ár(int diák_ár) {
        this.Diák_ár.set(diák_ár);
    }
}
