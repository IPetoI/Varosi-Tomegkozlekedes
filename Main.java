import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Image kep = new Image(getClass().getResourceAsStream("Képek/Ikon.png"));

        Parent root = FXMLLoader.load(getClass().getResource("Kezdolap.fxml"));
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.setTitle("Városi tömegközlekedés");
        primaryStage.getIcons().add(kep);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

    }
}

