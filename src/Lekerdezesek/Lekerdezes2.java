package Lekerdezesek;

import SQL.DbHandler;
import TablaSG.Iranyitoszamesjegysg;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Lekerdezes2 implements Initializable {

    @FXML
    private TableColumn<Iranyitoszamesjegysg, String> Város_Név;

    @FXML
    private TableColumn<Iranyitoszamesjegysg, Integer> Diák_ár;

    @FXML
    private TableView<Iranyitoszamesjegysg> iranyitoszamesjegy;

    @FXML
    private TextField Kereso;

    @FXML
    private BarChart<?, ?> Bar;


    private Connection conn;
    private ObservableList<Iranyitoszamesjegysg> list;
    private SQL.DbHandler DbHandler;

    private Stage stage;
    private Scene scene;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DbHandler =new DbHandler();
        SQL();
    }

    @FXML
    private void SQL() {
        try {

            list = FXCollections.observableArrayList();

            String SQL = "SELECT iranyitoszam.Város_Név,jegy.Diák_ár FROM iranyitoszam INNER JOIN jegy ON " +
                    "iranyitoszam.Irányítószám=jegy.Irányítószám WHERE iranyitoszam.Irányítószám IN(SELECT Irányítószám" +
                    " FROM jegy WHERE Diák_ár<500 )AND jegy.Diák_ár IN(SELECT Diák_ár FROM jegy WHERE Diák_ár<500 );";

            conn = DbHandler.getConnection();
            ResultSet set3 = conn.createStatement().executeQuery(SQL);
            XYChart.Series set5 = new XYChart.Series<>();

            while (set3.next()) {
                Iranyitoszamesjegysg jarmus = new Iranyitoszamesjegysg();
                jarmus.setVáros_Név(set3.getString(1));
                jarmus.setDiák_ár(set3.getInt(2));
                set5.getData().add(new XYChart.Data(set3.getString(1), set3.getInt(2)));
                list.add(jarmus);

            }

            Bar.getData().addAll(set5);

            Város_Név.setCellValueFactory(new PropertyValueFactory<>("Város_Név"));
            Diák_ár.setCellValueFactory(new PropertyValueFactory<>("Diák_ár"));
            iranyitoszamesjegy.setItems(list);

            FilteredList<Iranyitoszamesjegysg> szurendo = new FilteredList<>(list, b -> true);

            Kereso.textProperty().addListener((obs,regi,uj) -> {
                szurendo.setPredicate(Iranyitoszamesjegysg -> {

                    if (uj.isEmpty() || uj.isBlank() || uj== null) {
                        return true;
                    }

                    String kulcsszo = uj.toLowerCase();

                    if (Iranyitoszamesjegysg.getVáros_Név().toLowerCase().contains(kulcsszo)) {
                        return true;
                    }else if (Iranyitoszamesjegysg.getDiák_ár().toString().contains(kulcsszo)) {
                        return true;
                    } else
                        return false;
                });
            });

            SortedList<Iranyitoszamesjegysg> megtalalt = new SortedList <>(szurendo);
            megtalalt.comparatorProperty().bind(iranyitoszamesjegy.comparatorProperty());
            iranyitoszamesjegy.setItems(megtalalt);

        } catch (SQLException ex) {
            Logger.getLogger(Lekerdezes2.class.getName()).log(Level.SEVERE,null,ex);
        }
    }



    public void Tablanezobe(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_Nez/JarmuTabla.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void Kezdokepernyore(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../Kezdolap.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,1000,800);
        stage.setScene(scene);
        stage.show();
    }

    public void Tablaszerkesztobe(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_Szer/JarmuTablaSz.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void Lekerdezesekbe(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Lekerdezesek.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 800);
        stage.setScene(scene);
        stage.show();
    }
}
