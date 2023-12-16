package org.teambravo.pipergames.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import javafx.util.StringConverter;
import org.teambravo.pipergames.controller.GameController;
import org.teambravo.pipergames.controller.PlayerController;
import org.teambravo.pipergames.controller.TeamController;
import org.teambravo.pipergames.entity.Game;
import org.teambravo.pipergames.entity.Person;
import org.teambravo.pipergames.entity.Player;
import org.teambravo.pipergames.entity.Team;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class PlayerTabController implements Initializable {
    @FXML
    private Button allPlayersButton;
    @FXML
    private TableView<Team> teamTable;
    @FXML
    private TableColumn<Team, String> teamNameCol;
    @FXML
    private TableView<Player> playerTable;
    @FXML
    private TableColumn<Player, String> firstNameCol;
    @FXML
    private TableColumn<Player, String> lastNameCol;
    @FXML
    private TableColumn<Player, String> nickNameCol;
    @FXML
    private TextField firstNameText;
    @FXML
    private TextField lastNameText;
    @FXML
    private TextField nickNameText;
    @FXML
    private TextField streetAdressText;
    @FXML
    private TextField zipText;
    @FXML
    private TextField cityText;
    @FXML
    private TextField countryText;
    @FXML
    private TextField eMailText;
    @FXML
    private TextField teamText;
    @FXML
    private ComboBox<Game> gameCmb;
    @FXML
    private ComboBox<Team> teamCmb;

    @FXML
    protected void handleAllPlayersButtonAction(ActionEvent e) {
        List<Player> players = new PlayerController().getAllPlayer(false);
        ObservableList<Player> items = FXCollections.observableList(players);
        firstNameCol.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getPerson().getFirstName()));
        lastNameCol.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getPerson().getLastName()));
        nickNameCol.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getPerson().getNickName()));
        playerTable.setItems(items);
    }

    @FXML
    protected void handleAllPlayersInTeamsButtonAction(ActionEvent e) {
        List<Team> teams = new TeamController().getAllTeams(false);
        ObservableList<Team> items = FXCollections.observableList(teams);
        teamNameCol.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getName()));
        teamTable.setItems(items);
    }

    @FXML
    protected void HandleAddPlayerButtonAction(ActionEvent e) {
        if (!(firstNameText.getText().isEmpty() || lastNameText.getText().isEmpty() || nickNameText.getText().isEmpty())) {
            Player player = new Player();
            player.setPerson(new Person());
            PlayerController playerController = new PlayerController();
            player.getPerson().setFirstName(firstNameText.getText());
            player.getPerson().setLastName(lastNameText.getText());
            player.getPerson().setNickName(nickNameText.getText());
            player.getPerson().setAddress(streetAdressText.getText());
            player.getPerson().setPostalCode(zipText.getText());
            player.getPerson().setCity(cityText.getText());
            player.getPerson().setCountry(countryText.getText());
            player.getPerson().setEmail(eMailText.getText());
            player.setGame(gameCmb.getSelectionModel().getSelectedItem());
            player.setTeam(teamCmb.getSelectionModel().getSelectedItem());
            playerController.savePlayer(player);
        }
    }

    @FXML
    protected void HandleSavePlayerButtonAction(ActionEvent e) {
        Player player = playerTable.getSelectionModel().getSelectedItem();
        if (player != null) {
            PlayerController playerController = new PlayerController();
            player.getPerson().setFirstName(firstNameText.getText());
            player.getPerson().setLastName(lastNameText.getText());
            player.getPerson().setNickName(nickNameText.getText());
            player.getPerson().setAddress(streetAdressText.getText());
            player.getPerson().setPostalCode(zipText.getText());
            player.getPerson().setCity(cityText.getText());
            player.getPerson().setCountry(countryText.getText());
            player.getPerson().setEmail(eMailText.getText());
            player.setGame(gameCmb.getSelectionModel().getSelectedItem());
            player.setTeam(teamCmb.getSelectionModel().getSelectedItem());
            playerController.update(player);
        }
    }

    @FXML
    protected void HandleDeletePlayerButtonAction(ActionEvent e) {
        Player player = playerTable.getSelectionModel().getSelectedItem();
        if (player != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Ta bort");
            alert.setHeaderText("");
            alert.setContentText("Är du säker på att du vill ta bort valde spelar?");
            PlayerController playerController = new PlayerController();
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                if (playerController.deletePlayer(player)) {
                    playerTable.getItems().remove(player);
                }
            }
        }
    }

    Callback<ListView<Game>, ListCell<Game>> gameCellFactory = new Callback<ListView<Game>, ListCell<Game>>() {
        @Override
        public ListCell<Game> call(ListView<Game> l) {
            return new ListCell<Game>() {
                @Override
                protected void updateItem(Game item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        setText(item.getName());
                    }
                }
            };
        }
    };

    Callback<ListView<Team>, ListCell<Team>> teamCellFactory = new Callback<ListView<Team>, ListCell<Team>>() {
        @Override
        public ListCell<Team> call(ListView<Team> l) {
            return new ListCell<Team>() {
                @Override
                protected void updateItem(Team item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        setText(item.getName());
                    }
                }
            };
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        teamTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ObservableList<Team> teamSelectedItems = teamTable.getSelectionModel().getSelectedItems();
        teamSelectedItems.addListener(
            new ListChangeListener<Team>() {
                @Override
                public void onChanged(Change<? extends Team> change) {
                    playerTable.getItems().clear();
                    for (Team team:
                            teamTable.getSelectionModel().getSelectedItems()) {
                        List<Player> players = team.getPlayers();
                        ObservableList<Player> items = FXCollections.observableList(players);
                        firstNameCol.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getPerson().getFirstName()));
                        lastNameCol.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getPerson().getLastName()));
                        nickNameCol.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getPerson().getNickName()));
                        playerTable.getItems().addAll(items);
                    }
                }
            }
        );

        ObservableList<Player> playerSelectedItems = playerTable.getSelectionModel().getSelectedItems();
        playerSelectedItems.addListener(
            new ListChangeListener<Player>() {
                @Override
                public void onChanged(Change<? extends Player> change) {
                    if (change.getList().size() == 1) {
                        Player player = change.getList().get(0);
                        firstNameText.setText(player.getPerson().getFirstName());
                        lastNameText.setText(player.getPerson().getLastName());
                        nickNameText.setText(player.getPerson().getNickName());
                        streetAdressText.setText(player.getPerson().getAddress());
                        zipText.setText(player.getPerson().getPostalCode());
                        cityText.setText(player.getPerson().getCity());
                        countryText.setText(player.getPerson().getCountry());
                        eMailText.setText(player.getPerson().getEmail());
                        gameCmb.setValue(player.getGame());
                        teamCmb.setValue(player.getTeam());
                    } else {
                        firstNameText.setText("");
                        lastNameText.setText("");
                        nickNameText.setText("");
                        streetAdressText.setText("");
                        zipText.setText("");
                        cityText.setText("");
                        countryText.setText("");
                        eMailText.setText("");
                        gameCmb.getSelectionModel().clearSelection();
                        teamCmb.getSelectionModel().clearSelection();
                    }
                }
            }
        );

        List<Game> games = new GameController().getAll();
        ObservableList<Game> gameItems = FXCollections.observableList(games);
        gameCmb.setItems(gameItems);
        // Just set the button cell here:
        gameCmb.setButtonCell(gameCellFactory.call(null));
        gameCmb.setCellFactory(gameCellFactory);
        gameCmb.setConverter(new StringConverter<Game>() {
            @Override
            public String toString(Game object) {
                if (object == null) {
                    return "";
                }else {
                    return object.getName();
                }
            }

            @Override
            public Game fromString(String string) {
                return null;
            }
        });

        List<Team> teams = new TeamController().getAllTeams(false);
        ObservableList<Team> teamItems = FXCollections.observableList(teams);
        teamCmb.setItems(teamItems);
        // Just set the button cell here:
        teamCmb.setButtonCell(teamCellFactory.call(null));
        teamCmb.setCellFactory(teamCellFactory);
        teamCmb.setConverter(new StringConverter<Team>() {
            @Override
            public String toString(Team object) {
                if (object == null) {
                    return "";
                } else {
                    return object.getName();
                }
            }

            @Override
            public Team fromString(String string) {
                return null;
            }
        });
    }
}
