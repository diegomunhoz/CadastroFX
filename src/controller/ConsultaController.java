package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import dao.ClienteDAO;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Cliente;

public class ConsultaController implements Initializable {

    private ObservableList<ClienteTable> data;
    private List<Cliente> clientes;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button jbtFechar;
    @FXML
    private TableColumn<ClienteTable, String> colCidade;
    @FXML
    private TableColumn<ClienteTable, String> colCodigo;
    @FXML
    private TableColumn<ClienteTable, String> colIdade;
    @FXML
    private TableColumn<ClienteTable, String> colNome;
    @FXML
    private TextField edtPesquisa;
    @FXML
    private TableView<ClienteTable> table;

    @FXML
    void edtPesquisaAction(KeyEvent event) {

        try {
            clientes = new ClienteDAO().buscarPorNome(edtPesquisa.getText());
        } catch (SQLException erro) {
            erro.printStackTrace();
        } catch (Exception erro) {
            erro.printStackTrace();
        }

        data = FXCollections.observableArrayList();

        for (int i = 0; i < clientes.size(); i++) {
            data.add(new ClienteTable(Integer.toString(clientes.get(i).getCodigo()), clientes
                    .get(i).getNome(), clientes.get(i).getCidade(), Integer.toString(clientes
                    .get(i).getIdade())));
        }
        table.setItems(data);
    }

    @FXML
    public void initialize(URL arg0, ResourceBundle arg1) {
        assert colCidade != null : "fx:id=\"colCidade\" was not injected: check your FXML file 'TelaConsultaCliente.fxml'.";
        assert colCodigo != null : "fx:id=\"colCodigo\" was not injected: check your FXML file 'TelaConsultaCliente.fxml'.";
        assert colIdade != null : "fx:id=\"colIdade\" was not injected: check your FXML file 'TelaConsultaCliente.fxml'.";
        assert colNome != null : "fx:id=\"colNome\" was not injected: check your FXML file 'TelaConsultaCliente.fxml'.";
        assert edtPesquisa != null : "fx:id=\"edtPesquisa\" was not injected: check your FXML file 'TelaConsultaCliente.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'TelaConsultaCliente.fxml'.";

        jbtFechar.setGraphic(new ImageView(new Image("icones/fechar.png")));

        try {
            clientes = new ClienteDAO().buscarTodos();
        } catch (SQLException erro) {
            erro.printStackTrace();
        } catch (Exception erro) {
            erro.printStackTrace();
        }

        data = FXCollections.observableArrayList();

        for (int i = 0; i < clientes.size(); i++) {
            data.add(new ClienteTable(Integer.toString(clientes.get(i).getCodigo()), clientes
                    .get(i).getNome(), clientes.get(i).getCidade(), Integer.toString(clientes.
                    get(i).getIdade())));
        }

        colCodigo.setCellValueFactory(new PropertyValueFactory<ClienteTable, String>(
                "codigo"));
        colNome.setCellValueFactory(new PropertyValueFactory<ClienteTable, String>(
                "nome"));
        colIdade.setCellValueFactory(new PropertyValueFactory<ClienteTable, String>(
                "idade"));
        colCidade.setCellValueFactory(new PropertyValueFactory<ClienteTable, String>(
                "cidade"));

        table.setItems(data);
    }

    @FXML
    void fechar(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    /*
     * Classes para controle da tabela
     */
    public static class ClienteTable {

        private final SimpleStringProperty codigo;
        private final SimpleStringProperty nome;
        private final SimpleStringProperty idade;
        private final SimpleStringProperty cidade;

        private ClienteTable(String codigo, String nome, String idade,
                String ciadade) {
            this.codigo = new SimpleStringProperty(codigo);
            this.nome = new SimpleStringProperty(nome);
            this.idade = new SimpleStringProperty(idade);
            this.cidade = new SimpleStringProperty(ciadade);
        }

        public String getCodigo() {
            return codigo.get();
        }

        public String getNome() {
            return nome.get();
        }

        public String getIdade() {
            return idade.get();
        }

        public String getCidade() {
            return cidade.get();
        }
    }
}
