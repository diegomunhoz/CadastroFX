package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.stage.Stage;

/**
 *
 * @author Diego Munhoz
 */
public class MenuController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Menu itemMenuCadastro;

    @FXML
    private Menu itemMenuConsulta;   

    @FXML
    void cadastroCliente(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/FxmlCadastro.fxml"), resources);
            Stage stage = new Stage();
            stage.setTitle("Cadastro de Cliente");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }

    @FXML
    void consultaCliente(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/FxmlConsulta.fxml"), resources);
            Stage stage = new Stage();
            stage.setTitle("Consulta de Cliente");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }

    @FXML
    void initialize() {
        assert itemMenuCadastro != null : "fx:id=\"itemMenuCadastro\" was not injected: check your FXML file 'FxmlMenu.fxml'.";
        assert itemMenuConsulta != null : "fx:id=\"itemMenuConsulta\" was not injected: check your FXML file 'FxmlMenu.fxml'.";
    }
}