package Lekerdezesek;

import SQL.DbHandler;
import TablaSG.Jarmu_azonositoesmenetrendsg;
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

public class Lekerdezes6 implements Initializable {

    @FXML
    private TableColumn<Jarmu_azonositoesmenetrendsg, String> Járatszám;

    @FXML
    private TableColumn<Jarmu_azonositoesmenetrendsg, Integer> Beér;

    @FXML
    private TableView<Jarmu_azonositoesmenetrendsg> jarmu_azonositoesmenetrend;

    @FXML
    private TextField Kereso;


    private Connection conn;
    private ObservableList<Jarmu_azonositoesmenetrendsg> list;
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
            String SQL = "SELECT MAX(menetrend.beér),jarmu_azonosito.Járatszám FROM menetrend INNER JOIN jarmu_azonosito ON menetrend.Jármű_Azonosító=" +
                    "jarmu_azonosito.Jármű_Azonosító WHERE beér=(SELECT MAX(beér) FROM menetrend)";

            conn = DbHandler.getConnection();
            ResultSet set3 = conn.createStatement().executeQuery(SQL);

            while (set3.next()) {
                Jarmu_azonositoesmenetrendsg jarmus = new Jarmu_azonositoesmenetrendsg();
                jarmus.setBeér(set3.getString(1));
                jarmus.setJáratszám(set3.getString(2));
                list.add(jarmus);

            }

            Beér.setCellValueFactory(new PropertyValueFactory<>("Beér"));
            Járatszám.setCellValueFactory(new PropertyValueFactory<>("Járatszám"));
            jarmu_azonositoesmenetrend.setItems(list);

            FilteredList<Jarmu_azonositoesmenetrendsg> szurendo = new FilteredList<>(list, b -> true);

            Kereso.textProperty().addListener((obs,regi,uj) -> {
                szurendo.setPredicate(Jarmu_azonositoesmenetrendsg -> {

                    if (uj.isEmpty() || uj.isBlank() || uj== null) {
                        return true;
                    }

                    String kulcsszo = uj.toLowerCase();

                    if (Jarmu_azonositoesmenetrendsg.getJáratszám().toLowerCase().contains(kulcsszo)) {
                        return true;
                    }else if (Jarmu_azonositoesmenetrendsg.getBeér().contains(kulcsszo)) {
                        return true;
                    } else
                        return false;
                });
            });

            SortedList<Jarmu_azonositoesmenetrendsg> megtalalt = new SortedList <>(szurendo);
            megtalalt.comparatorProperty().bind(jarmu_azonositoesmenetrend.comparatorProperty());
            jarmu_azonositoesmenetrend.setItems(megtalalt);

        } catch (SQLException ex) {
            Logger.getLogger(Lekerdezes6.class.getName()).log(Level.SEVERE,null,ex);
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
