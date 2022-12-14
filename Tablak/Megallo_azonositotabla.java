package Tablak;

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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Megallo_azonositotabla implements Initializable {

    @FXML
    private TableColumn<Megallo_azonositosg, Integer> Megálló_Azonosító;

    @FXML
    private TableColumn<Megallo_azonositosg, String> Megálló_Név;
    
    @FXML
    private TableView<Megallo_azonositosg> megallo_azonosito;

    @FXML
    private TextField Kereso;

    @FXML
    private TextField MegalloNev;

    @FXML
    private Label informacio;

    @FXML
    private ChoiceBox<Integer> valaszthato;

    private Connection conn;
    private ObservableList<Megallo_azonositosg> list;
    private SQL.DbHandler DbHandler;
    private PreparedStatement pst;
    int sorszam=-1;

    private Stage stage;
    private Scene scene;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DbHandler =new DbHandler();

        populateTableView();
        MegalloAzonositok();
    }

    public void Hozzaad() {

        conn=DbHandler.getConnection();
        String sql = "INSERT INTO megallo_azonosito (Megálló_Azonosító,Megálló_Név)VALUES(?,? )";

        try {
            pst= conn.prepareStatement(sql);
            pst.setString(2, MegalloNev.getText());
            pst.setInt(1, valaszthato.getValue());
            pst.execute();

            informacio.setTextFill(Color.color(0, 0.65, 0));
            informacio.setText("Megálló Azonosító hozzáadva a táblához.");

            populateTableView();

        } catch (SQLException throwables) {
            throwables.printStackTrace();

            informacio.setTextFill(Color.color(0.9, 0, 0));
            informacio.setText("Sikertelen hozzáadás a Megálló Azonosító táblához, próbáld újra.");
        }
    }

    public void Szerkeszt() {
        try {
            conn=DbHandler.getConnection();

            String value2 = MegalloNev.getText();
            Integer value1 = valaszthato.getValue();

            String sql = "UPDATE megallo_azonosito SET Megálló_Azonosító= '"+value1+"' ,Megálló_Név= '"+value2+"' WHERE  Megálló_Azonosító= '"+value1+"' ";

            pst=conn.prepareStatement(sql);
            System.out.println(sql);
            pst.execute();

            informacio.setTextFill(Color.color(0, 0.65, 0));
            informacio.setText("Megálló Azonosító tábla értéke szerkesztve lett.");

            populateTableView();

        } catch (Exception e) {
            e.printStackTrace();

            informacio.setTextFill(Color.color(0.9, 0, 0));
            informacio.setText("Sikertelen a Megálló Azonosító tábla érték szerkesztése, próbáld újra.");
        }
    }

    public void Torol() {
        conn=DbHandler.getConnection();
        String sql = "DELETE FROM megallo_azonosito WHERE Megálló_Azonosító= ?";

        try {
            pst= conn.prepareStatement(sql);
            pst.setInt(1, valaszthato.getValue());
            pst.execute();

            informacio.setTextFill(Color.color(0, 0.65, 0));
            informacio.setText("Megálló Azonosító törölve a táblából.");

            populateTableView();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            informacio.setTextFill(Color.color(0.9, 0, 0));
            informacio.setText("Sikertelen törlés a Megálló Azonosító táblából, próbáld újra.");
        }
    }


    public void Kivalaszt(javafx.scene.input.MouseEvent mouseEvent) {
        sorszam = megallo_azonosito.getSelectionModel().getSelectedIndex();
        if (sorszam <= -1) {
            return;
        }
        valaszthato.setValue(Megálló_Azonosító.getCellData(sorszam));
        MegalloNev.setText(Megálló_Név.getCellData(sorszam));
    }

    private void MegalloAzonositok() {
        conn = DbHandler.getConnection();
        ObservableList<Integer> lista= FXCollections.observableArrayList();
        try {
            ResultSet sete = conn.createStatement().executeQuery("SELECT Megálló_Azonosító FROM megallo");

            while (sete.next()) {
                lista.add(sete.getInt("Megálló_Azonosító"));
            }

        } catch(SQLException ex){
            Logger.getLogger(Megallotabla.class.getName()).log(Level.SEVERE, null, ex);
        }
        valaszthato.setItems(null);
        valaszthato.setItems(lista);
        valaszthato.setValue(lista.get(0));
    }



    private void populateTableView()  {
        try {
            list= FXCollections.observableArrayList();
            String quary="SELECT * FROM megallo_azonosito";

            conn=DbHandler.getConnection();
            ResultSet set = conn.createStatement().executeQuery(quary);

            while (set.next()) {
                Megallo_azonositosg megalloaz=new Megallo_azonositosg();
                megalloaz.setMegálló_Név(set.getString("Megálló_Név"));
                megalloaz.setMegálló_Azonosító(set.getInt("Megálló_Azonosító"));

                list.add(megalloaz);
            }

            Megálló_Azonosító.setCellValueFactory(new PropertyValueFactory<>("Megálló_Azonosító"));
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
                    } else if (Megallo_azonositosg.getMegálló_Azonosító().toString().contains(kulcsszo)) {
                        return true;
                    } else
                        return false;
                });
            });

            SortedList<Megallo_azonositosg> megtalalt = new SortedList <>(szurendo);
            megtalalt.comparatorProperty().bind(megallo_azonosito.comparatorProperty());
            megallo_azonosito.setItems(megtalalt);

        } catch (SQLException ex) {
            Logger.getLogger(Megallo_azonositotabla.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    public void Lekerdezesekbe(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../Lekerdezesek/Lekerdezesek.fxml"));
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

    public void JarmuTablara(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_Nez/JarmuTabla.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void Tablaszerkesztobe(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_Szer/MegalloazonositoTablaSz.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 800);
        stage.setScene(scene);
        stage.show();
    }


    public void BuszTablara(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_Nez/BuszTabla.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void IranyitoszamTablara(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_Nez/IranyitoszamTabla.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void JarmuAzonositoTablara(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_Nez/JarmuAzonositoTabla.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void JegyTablara(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_Nez/JegyTabla.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void MegalloAzonositoTablara(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_Nez/MegalloazonositoTabla.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void MenetrendTablara(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_Nez/MenetrendTabla.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void MetroTablara(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_Nez/MetroTabla.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void TroliTablara(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_Nez/TroliTabla.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void VarosTablara(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_Nez/VarosTabla.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void VillamosTablara(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_Nez/VillamosTabla.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void MegalloTablara(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_Nez/MegalloTabla.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void MegalloTipusTablara(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_Nez/MegalloTipusTabla.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void BuszTablaraSz(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_Szer/BuszTablaSz.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void IranyitoszamTablaraSz(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_Szer/IranyitoszamTablaSz.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void JarmuAzonositoTablaraSz(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_Szer/JarmuAzonositoTablaSz.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void JegyTablaraSz(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_Szer/JegyTablaSz.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void MegalloAzonositoTablaraSz(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_Szer/MegalloazonositoTablaSz.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void MenetrendTablaraSz(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_Szer/MenetrendTablaSz.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void MetroTablaraSz(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_Szer/MetroTablaSz.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void TroliTablaraSz(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_Szer/TroliTablaSz.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void VarosTablaraSz(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_Szer/VarosTablaSz.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void VillamosTablaraSz(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_Szer/VillamosTablaSz.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void MegalloTablaraSz(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_Szer/MegalloTablaSz.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void MegalloTipusTablaraSz(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_Szer/MegalloTipusTablaSz.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void JarmuTablaraSz(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_Szer/JarmuTablaSz.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void Tablanezobe(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_Nez/MegalloazonositoTabla.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 800);
        stage.setScene(scene);
        stage.show();
    }


}
