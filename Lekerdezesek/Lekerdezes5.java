package Lekerdezesek;

import SQL.DbHandler;
import TablaSG.Buszsg;
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

public class Lekerdezes5 implements Initializable {

    @FXML
    private TableColumn<Buszsg, Integer> Férőhely_szám;

    @FXML
    private TableColumn<Buszsg, Integer> Jármű_Azonosító;

    @FXML
    private TableView<Buszsg> bus;

    @FXML
    private TextField Kereso;

    private Connection conn;
    private ObservableList<Buszsg> list;
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
            String SQL = "SELECT COUNT(Jármű_Azonosító),ROUND(AVG(Férőhely_szám), 0) FROM busz";

            conn = DbHandler.getConnection();
            ResultSet set3 = conn.createStatement().executeQuery(SQL);

            while (set3.next()) {
                Buszsg jarmus = new Buszsg();
                jarmus.setJármű_Azonosító(set3.getInt(1));
                jarmus.setFérőhely_szám(set3.getInt(2));
                list.add(jarmus);

            }

            Jármű_Azonosító.setCellValueFactory(new PropertyValueFactory<>("Jármű_Azonosító"));
            Férőhely_szám.setCellValueFactory(new PropertyValueFactory<>("Férőhely_szám"));
            bus.setItems(list);

            FilteredList<Buszsg> szurendo = new FilteredList<>(list, b -> true);

            Kereso.textProperty().addListener((obs,regi,uj) -> {
                szurendo.setPredicate(Buszsg -> {

                    if (uj.isEmpty() || uj.isBlank() || uj== null) {
                        return true;
                    }

                    String kulcsszo = uj.toLowerCase();

                    if (Buszsg.getJármű_Azonosító().toString().contains(kulcsszo)) {
                        return true;
                    } else if (Buszsg.getFérőhely_szám().toString().contains(kulcsszo)) {
                        return true;
                    } else
                        return false;
                });
            });

            SortedList<Buszsg> megtalalt = new SortedList <>(szurendo);
            megtalalt.comparatorProperty().bind(bus.comparatorProperty());
            bus.setItems(megtalalt);

        } catch (SQLException ex) {
            Logger.getLogger(Lekerdezes5.class.getName()).log(Level.SEVERE,null,ex);
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
