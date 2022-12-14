package Lekerdezesek;

import SQL.DbHandler;
import TablaSG.Jarmu_azonositosg;
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
import javafx.scene.control.*;
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

public class Lekerdezes0 implements Initializable {

    @FXML
    private TableColumn<Jarmu_azonositosg, String> Járatszám;

    @FXML
    private TableView<Jarmu_azonositosg> jarmu_azonosito;

    @FXML
    private TextField Kereso;


    private Connection conn;
    private ObservableList<Jarmu_azonositosg> list;
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
            String SQL = "SELECT `Járatszám` FROM `jarmu_azonosito` WHERE `Jármű_Azonosító`IN(SELECT Jármű_Azonosító FROM " +
                    "jarmu WHERE Irányítószám=(SELECT Irányítószám FROM iranyitoszam WHERE Város_Név='Szeged'))";

            conn = DbHandler.getConnection();
            ResultSet set3 = conn.createStatement().executeQuery(SQL);

            while (set3.next()) {
                Jarmu_azonositosg jarmus = new Jarmu_azonositosg();
                jarmus.setJáratszám(set3.getString(1));
                list.add(jarmus);

            }

            Járatszám.setCellValueFactory(new PropertyValueFactory<>("Járatszám"));
            jarmu_azonosito.setItems(list);

            FilteredList<Jarmu_azonositosg> szurendo = new FilteredList<>(list, b -> true);

            Kereso.textProperty().addListener((obs,regi,uj) -> {
                szurendo.setPredicate(Jarmu_azonositosg -> {

                    if (uj.isEmpty() || uj.isBlank() || uj== null) {
                        return true;
                    }

                    String kulcsszo = uj.toLowerCase();

                    if (Jarmu_azonositosg.getJáratszám().contains(kulcsszo)) {
                        return true;
                    } else
                        return false;
                });
            });

            SortedList<Jarmu_azonositosg> megtalalt = new SortedList <>(szurendo);
            megtalalt.comparatorProperty().bind(jarmu_azonosito.comparatorProperty());
            jarmu_azonosito.setItems(megtalalt);

        } catch (SQLException ex) {
            Logger.getLogger(Lekerdezes0.class.getName()).log(Level.SEVERE,null,ex);
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
