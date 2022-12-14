package Lekerdezesek;

import SQL.DbHandler;
import TablaSG.Megallo_azonositosg;
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

public class Lekerdezes1 implements Initializable {

    @FXML
    private TableColumn<Megallo_azonositosg, String> Megálló_Név;

    @FXML
    private TableView<Megallo_azonositosg> megallo_azonosito;

    @FXML
    private TextField Kereso;


    private Connection conn;
    private ObservableList<Megallo_azonositosg> list;
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
            String SQL = "SELECT `Megálló_Név` FROM `megallo_azonosito` WHERE `Megálló_Azonosító`IN(SELECT Megálló_Azonosító" +
                    " FROM megallo WHERE Irányítószám=(SELECT Irányítószám FROM iranyitoszam WHERE Város_Név='Budapest')) ORDER BY Megálló_Név ";

            conn = DbHandler.getConnection();
            ResultSet set3 = conn.createStatement().executeQuery(SQL);

            while (set3.next()) {
                Megallo_azonositosg jarmus = new Megallo_azonositosg();
                jarmus.setMegálló_Név(set3.getString(1));
                list.add(jarmus);

            }

            Megálló_Név.setCellValueFactory(new PropertyValueFactory<>("Megálló_Név"));
            megallo_azonosito.setItems(list);

            FilteredList<Megallo_azonositosg> szurendo = new FilteredList<>(list, b -> true);

            Kereso.textProperty().addListener((obs,regi,uj) -> {
                szurendo.setPredicate(Megallo_azonositosg -> {

                    if (uj.isEmpty() || uj.isBlank() || uj== null) {
                        return true;
                    }

                    String kulcsszo = uj.toLowerCase();

                    if (Megallo_azonositosg.getMegálló_Név().toLowerCase().contains(kulcsszo)) {
                        return true;
                    } else
                        return false;
                });
            });

            SortedList<Megallo_azonositosg> megtalalt = new SortedList <>(szurendo);
            megtalalt.comparatorProperty().bind(megallo_azonosito.comparatorProperty());
            megallo_azonosito.setItems(megtalalt);

        } catch (SQLException ex) {
            Logger.getLogger(Lekerdezes1.class.getName()).log(Level.SEVERE,null,ex);
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
