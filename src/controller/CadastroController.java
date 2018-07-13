package controller;

import dao.ClienteDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;
import model.Cliente;

public class CadastroController implements Initializable{

    private int idade;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button jbtCancelar;
    @FXML
    private Button jbtFechar;
    @FXML
    private Button jbtSalvar;
    @FXML
    private TextField txtCidade;
    @FXML
    private TextField txtIdade;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtLogin;
    @FXML
    private TextField txtSenha;

    @FXML
    void cancelar(ActionEvent event) {
        limparTela();
    }
    
    @FXML
    void fechar(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void salvar(ActionEvent event) {
        if (validar()) {
            ClienteDAO dao = new ClienteDAO();
            Cliente cliente = new Cliente();
            cliente.setNome(txtNome.getText());
            cliente.setCidade(txtCidade.getText());
            cliente.setIdade(idade);
            cliente.setLogin(txtLogin.getText());
            cliente.setSenha(txtSenha.getText());
            try {
                dao.salvar(cliente);
                limparTela();
                JOptionPane.showMessageDialog(null, "Cliente salvo com sucesso!");
            } catch (SQLException ex) {
                Logger.getLogger(CadastroController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        jbtCancelar.setGraphic(new ImageView(new Image("icones/cancelar.png")));
        jbtSalvar.setGraphic(new ImageView(new Image("icones/salvar.png")));
        jbtFechar.setGraphic(new ImageView(new Image("icones/fechar.png")));
    }

    public boolean validar() {
        if (txtNome.getText().equals(null) || txtNome.getText().equals("") || txtNome.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe o nome, campo obrigatório!");
            return false;
        }
        if (txtIdade.getText().equals(null) || txtIdade.getText().equals("") || txtIdade.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe a idade, campo obrigatório!");
            return false;
        } else {
            try {
                idade = Integer.parseInt(txtIdade.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Idade inválida, digite novamente!");
                return false;
            }
        }
        if (txtCidade.getText().equals(null) || txtCidade.getText().equals("") || txtCidade.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe cidade, campo obrigatório!");
            return false;
        }
        if (txtLogin.getText().equals(null) || txtLogin.getText().equals("") || txtLogin.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe o login, campo obrigatório!");
            return false;
        }
        if (txtSenha.getText().equals(null) || txtSenha.getText().equals("") || txtSenha.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe a senha, campo obrigatório!");
            return false;
        }
        return true;
    }

    public void limparTela() {
        txtNome.setText(null);
        txtCidade.setText(null);
        txtIdade.setText(null);
        txtLogin.setText(null);
        txtSenha.setText(null);
    }
}