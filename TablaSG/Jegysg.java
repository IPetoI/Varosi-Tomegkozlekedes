package TablaSG;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Jegysg {

    private final IntegerProperty Irányítószám = new SimpleIntegerProperty();
    private final IntegerProperty Normál_ár = new SimpleIntegerProperty();
    private final IntegerProperty Diák_ár = new SimpleIntegerProperty();
    private final IntegerProperty Nyugdíjas_ár = new SimpleIntegerProperty();
    private final IntegerProperty Kilencven_ár = new SimpleIntegerProperty();


    public Integer getIrányítószám() {
        return Irányítószám.get();
    }

    public IntegerProperty irányítószámProperty() {
        return Irányítószám;
    }

    public void setIrányítószám(int irányítószám) {
        this.Irányítószám.set(irányítószám);
    }

    public Integer getNormál_ár() {
        return Normál_ár.get();
    }

    public IntegerProperty normál_árProperty() {
        return Normál_ár;
    }

    public void setNormál_ár(int normál_ár) {
        this.Normál_ár.set(normál_ár);
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

    public Integer getNyugdíjas_ár() {
        return Nyugdíjas_ár.get();
    }

    public IntegerProperty nyugdíjas_árProperty() {
        return Nyugdíjas_ár;
    }

    public void setNyugdíjas_ár(int nyugdíjas_ár) {
        this.Nyugdíjas_ár.set(nyugdíjas_ár);
    }

    public Integer getKilencven_ár() {
        return Kilencven_ár.get();
    }

    public IntegerProperty Kilencven_árProperty() {
        return Kilencven_ár;
    }

    public void setKilencven_ár(int Kilencven_ár) {
        this.Kilencven_ár.set(Kilencven_ár);
    }
}
