import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent tela = FXMLLoader.load(getClass().getResource("fxml/FxmlCadastro.fxml"));
        stage.setTitle("Login do Sistema!");
        stage.setScene(new Scene(tela));
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}