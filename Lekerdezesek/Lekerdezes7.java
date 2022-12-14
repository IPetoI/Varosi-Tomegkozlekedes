package Lekerdezesek;

import SQL.DbHandler;
import TablaSG.Iranyitoszamesjarmusg;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
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

public class Lekerdezes7 implements Initializable {

    @FXML
    private TableColumn<Iranyitoszamesjarmusg, String> Város_Név;

    @FXML
    private TableColumn<Iranyitoszamesjarmusg, Integer> Irányítószám;

    @FXML
    private TableColumn<Iranyitoszamesjarmusg, Integer> Jármű_Azonosító;

    @FXML
    private TableView<Iranyitoszamesjarmusg> iranyitoszamesjarmu;

    @FXML
    private TextField Kereso;

    @FXML
    private BarChart<?, ?> Bar;


    private Connection conn;
    private ObservableList<Iranyitoszamesjarmusg> list;
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

            String SQL = "SELECT COUNT(jarmu.Jármű_Azonosító), jarmu.Irányítószám,iranyitoszam.Város_név FROM jarmu INNER JOIN iranyitoszam ON" +
                    " iranyitoszam.Irányítószám=jarmu.Irányítószám GROUP BY Irányítószám ORDER BY Város_Név";

            conn = DbHandler.getConnection();
            ResultSet set3 = conn.createStatement().executeQuery(SQL);
            XYChart.Series set5 = new XYChart.Series<>();

            while (set3.next()) {
                Iranyitoszamesjarmusg jarmus = new Iranyitoszamesjarmusg();
                jarmus.setVáros_Név(set3.getString(3));
                jarmus.setIrányítószám(set3.getInt(2));
                jarmus.setJármű_Azonosító(set3.getInt(1));
                set5.getData().add(new XYChart.Data(set3.getString(3), set3.getInt(1)));
                list.add(jarmus);

            }

            Bar.getData().addAll(set5);

            Város_Név.setCellValueFactory(new PropertyValueFactory<>("Város_Név"));
            Irányítószám.setCellValueFactory(new PropertyValueFactory<>("Irányítószám"));
            Jármű_Azonosító.setCellValueFactory(new PropertyValueFactory<>("Jármű_Azonosító"));
            iranyitoszamesjarmu.setItems(list);

            FilteredList<Iranyitoszamesjarmusg> szurendo = new FilteredList<>(list, b -> true);

            Kereso.textProperty().addListener((obs,regi,uj) -> {
                szurendo.setPredicate(Iranyitoszamesjarmusg -> {

                    if (uj.isEmpty() || uj.isBlank() || uj== null) {
                        return true;
                    }

                    String kulcsszo = uj.toLowerCase();

                    if (Iranyitoszamesjarmusg.getVáros_Név().toLowerCase().contains(kulcsszo)) {
                        return true;
                    }else if (Iranyitoszamesjarmusg.getIrányítószám().toString().contains(kulcsszo)) {
                        return true;
                    }else if (Iranyitoszamesjarmusg.getJármű_Azonosító().toString().contains(kulcsszo)) {
                        return true;
                    } else
                        return false;
                });
            });

            SortedList<Iranyitoszamesjarmusg> megtalalt = new SortedList <>(szurendo);
            megtalalt.comparatorProperty().bind(iranyitoszamesjarmu.comparatorProperty());
            iranyitoszamesjarmu.setItems(megtalalt);

        } catch (SQLException ex) {
            Logger.getLogger(Lekerdezes7.class.getName()).log(Level.SEVERE,null,ex);
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
