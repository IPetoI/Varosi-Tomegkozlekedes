package Tablak;

import SQL.DbHandler;
import TablaSG.Villamossg;
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

public class Villamostabla implements Initializable {

    @FXML
    private TableColumn<Villamossg, Integer> Kocsi_szám;

    @FXML
    private TableColumn<Villamossg, Integer> Jármű_Azonosító;

    @FXML
    private TableView<Villamossg> villamos;

    @FXML
    private TextField Kereso;

    @FXML
    private TextField KocsiSzam;

    @FXML
    private Label informacio;

    @FXML
    private ChoiceBox<Integer> valaszthato;

    private Connection conn;
    private ObservableList<Villamossg> list;
    private SQL.DbHandler DbHandler;
    private PreparedStatement pst;
    int sorszam=-1;

    private Stage stage;
    private Scene scene;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DbHandler =new DbHandler();

        populateTableView();
        Jaratazonositok();
    }

    public void Hozzaad() {

        conn=DbHandler.getConnection();
        String sql = "INSERT INTO villamos (Jármű_Azonosító,Kocsi_szám)VALUES(?,? )";

        try {
            pst= conn.prepareStatement(sql);
            pst.setInt(1, valaszthato.getValue());
            pst.setString(2, KocsiSzam.getText());
            pst.execute();

            informacio.setTextFill(Color.color(0, 0.65, 0));
            informacio.setText("Villamos hozzáadva a táblához.");

            populateTableView();

        } catch (SQLException throwables) {
            throwables.printStackTrace();

            informacio.setTextFill(Color.color(0.9, 0, 0));
            informacio.setText("Sikertelen hozzáadás a Villamos táblához, próbáld újra.");
        }
    }

    public void Szerkeszt() {
        try {
            conn=DbHandler.getConnection();

            Integer value1 = valaszthato.getValue();
            String value2 = KocsiSzam.getText();

            String sql = "UPDATE villamos SET Jármű_Azonosító= '"+value1+"' ,Kocsi_szám= '"+value2+"' WHERE  Jármű_Azonosító= '"+value1+"' ";

            pst=conn.prepareStatement(sql);
            System.out.println(sql);
            pst.execute();

            informacio.setTextFill(Color.color(0, 0.65, 0));
            informacio.setText("Villamos tábla értéke szerkesztve lett.");

            populateTableView();

        } catch (Exception e) {
            e.printStackTrace();

            informacio.setTextFill(Color.color(0.9, 0, 0));
            informacio.setText("Sikertelen a Villamos tábla érték szerkesztése, próbáld újra.");
        }
    }

    public void Torol() {
        conn=DbHandler.getConnection();
        String sql = "DELETE FROM villamos WHERE Jármű_Azonosító = ?";

        try {
            pst= conn.prepareStatement(sql);
            pst.setString(1, String.valueOf(valaszthato.getValue()));
            pst.execute();

            informacio.setTextFill(Color.color(0, 0.65, 0));
            informacio.setText("Villamos törölve a táblából.");

            populateTableView();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            informacio.setTextFill(Color.color(0.9, 0, 0));
            informacio.setText("Sikertelen törlés a Villamos táblából, próbáld újra.");
        }
    }

    public void Kivalaszt(javafx.scene.input.MouseEvent mouseEvent) {
        sorszam = villamos.getSelectionModel().getSelectedIndex();
        if (sorszam <= -1) {
            return;
        }
        KocsiSzam.setText(Kocsi_szám.getCellData(sorszam).toString());
        valaszthato.setValue(Jármű_Azonosító.getCellData(sorszam));
    }

    private void Jaratazonositok() {
        conn = DbHandler.getConnection();
        ObservableList<Integer> lista= FXCollections.observableArrayList();
        try {
            ResultSet sete = conn.createStatement().executeQuery("SELECT jarmu.Jármű_Azonosító FROM jarmu WHERE jarmu.Jármű_Azonosító NOT IN( SELECT troli.Jármű_Azonosító" +
                    " FROM troli ) AND jarmu.Jármű_Azonosító NOT IN( SELECT metro.Jármű_Azonosító FROM metro ) AND jarmu.Jármű_Azonosító NOT IN( SELECT busz.Jármű_Azonosító " +
                    "FROM busz )");

            while (sete.next()) {
                lista.add(sete.getInt("Jármű_Azonosító"));
            }

        } catch(SQLException ex){
            Logger.getLogger(Jarmutabla.class.getName()).log(Level.SEVERE, null, ex);
        }
        valaszthato.setItems(null);
        valaszthato.setItems(lista);
        valaszthato.setValue(lista.get(0));
    }



    private void populateTableView()  {
        try {
            list= FXCollections.observableArrayList();
            String quary="SELECT * FROM villamos";

            conn=DbHandler.getConnection();
            ResultSet set = conn.createStatement().executeQuery(quary);

            while (set.next()) {
                Villamossg villamosok=new Villamossg();
                villamosok.setJármű_Azonosító(set.getInt("Jármű_Azonosító"));
                villamosok.setKocsi_szám(set.getInt("Kocsi_szám"));

                list.add(villamosok);
            }

            Kocsi_szám.setCellValueFactory(new PropertyValueFactory<>("Kocsi_szám"));
            Jármű_Azonosító.setCellValueFactory(new PropertyValueFactory<>("Jármű_Azonosító"));
            villamos.setItems(list);

            FilteredList<Villamossg> szurendo = new FilteredList<>(list, b -> true);

            Kereso.textProperty().addListener((obs,regi,uj) -> {
                szurendo.setPredicate(Villamossg -> {

                    if (uj.isEmpty() || uj.isBlank() || uj== null) {
                        return true;
                    }

                    String kulcsszo = uj.toLowerCase();

                    if (Villamossg.getJármű_Azonosító().toString().contains(kulcsszo)) {
                        return true;
                    } else if (Villamossg.getKocsi_szám().toString().contains(kulcsszo)) {
                        return true;
                    } else
                        return false;
                });
            });

            SortedList<Villamossg> megtalalt = new SortedList <>(szurendo);
            megtalalt.comparatorProperty().bind(villamos.comparatorProperty());
            villamos.setItems(megtalalt);

        } catch (SQLException ex) {
            Logger.getLogger(Villamostabla.class.getName()).log(Level.SEVERE,null,ex);
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
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_Szer/VillamosTablaSz.fxml"));
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

    public void Tablanezobe(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_Nez/VillamosTabla.fxml"));
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
}
