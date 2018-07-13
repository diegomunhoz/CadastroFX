package controller;

import dao.ClienteDAO;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.Cliente;

public class LoginController implements Initializable {

    private String login;
    private String senha;
    @FXML
    private ResourceBundle resources;
    @FXML
    private Button jbtCancelar;
    @FXML
    private Button jbtOk;
    @FXML
    PasswordField txtSenha;
    @FXML
    TextField txtLogin;
    private String[] args;

    public void cancelar(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    public void ok(ActionEvent event) {
        if (validaDadosEntrada()) {
            if (validaLogin()) {
                Parent root;
                try {
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/FxmlMenu.fxml"), resources);
                    Stage stage = new Stage();
                    stage.setTitle("Menu Principal");
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        jbtCancelar.setGraphic(new ImageView(new Image("icones/cancelar.png")));
        jbtOk.setGraphic(new ImageView(new Image("icones/ok.png")));
    }

    public boolean validaDadosEntrada() {
        if (txtLogin.getText().equals(null) || txtLogin.getText().equals("") || txtLogin.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe o Login, campo obrigatório!");
            return false;
        }
        if (txtSenha.getText().equals(null) || txtSenha.getText().equals("") || txtSenha.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe a Senha, campo obrigatório!");
            return false;
        }
        return true;
    }

    public boolean validaLogin() {
        try {
            List<Cliente> listaCliente = new ClienteDAO().buscarTodos();
            for (Cliente cliente : listaCliente) {
                if (cliente.getLogin().equals(txtLogin.getText())) {
                    validaSenha();
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Login incorreto, digite novamente!");
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean validaSenha() {
        try {
            List<Cliente> listaCliente = new ClienteDAO().buscarTodos();
            for (Cliente cliente : listaCliente) {
                if (cliente.getSenha().equals(txtSenha.getText())) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Senha incorreta, digite novamente!");
                    return false;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}