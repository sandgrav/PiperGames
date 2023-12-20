package org.teambravo.pipergames.view;

import javafx.beans.Observable;
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
import org.teambravo.pipergames.controller.MatchSoloController;
import org.teambravo.pipergames.controller.PlayerController;
import org.teambravo.pipergames.controller.TournamentController;
import org.teambravo.pipergames.entity.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class TournamentTabController implements Initializable {
    @FXML
    private ComboBox<Game> gameCmb;
    @FXML
    private TableView<Tournament> tournamentTable;
    @FXML
    private TableColumn<Tournament, String> tournamentNameCol;
    @FXML
    private TableView<Player> playerTable;
    @FXML
    private TableColumn<Player, String> playerNameCol;
    @FXML
    private ComboBox spelLagCmb;
    @FXML
    private TextField tournamentNameText;
    @FXML
    private DatePicker dateQuarterFinal;
    @FXML
    private Button startFirstRoundButton;
    @FXML
    private DatePicker dateSemiFinal;
    @FXML
    private DatePicker dateFinal;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private Label label5;
    @FXML
    private Label label6;
    @FXML
    private Label label7;
    @FXML
    private Label label8;
    @FXML
    private Label label9;
    @FXML
    private Label label10;
    @FXML
    private Label label11;
    @FXML
    private Label label12;
    @FXML
    private Label label14;
    @FXML
    private Label label15;
    private Tournament tournament = null;

    @FXML
    protected void handleGameCmbOnAction(ActionEvent e) {
        TournamentController tournamentController = new TournamentController();
        PlayerController playerController = new PlayerController();
        Game selectedGame = gameCmb.getValue();
        List<Tournament> tournaments = tournamentController.getByGameId(selectedGame.getId());
        ObservableList<Tournament> tournamentItems = FXCollections.observableList(tournaments);
        tournamentNameCol.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getName()));
        tournamentTable.setItems(tournamentItems);
        List<Player> players = playerController.getByGameId(selectedGame.getId());
        ObservableList<Player> playerItems = FXCollections.observableList(players);
        playerNameCol.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getPerson().getNickName()));
        playerTable.setItems(playerItems);
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

    @FXML
    protected void handleStartFirstRoundButtonAction(ActionEvent e) {
        if (tournament != null) {
            Random random = new Random();
            int index;
            ObservableList<Player> selectedPlayers = playerTable.getSelectionModel().getSelectedItems();
            List<Player> players = new ArrayList<>();
            Player[] randomPlayers = new Player[8];
            for (int i = 0; i < selectedPlayers.size(); i++) {
                do {
                    index = random.nextInt(8);
                } while (randomPlayers[index] != null);
                randomPlayers[index] = selectedPlayers.get(i);
                tournament.addPlayer(selectedPlayers.get(i), 1, index);
            }
            label1.setText(randomPlayers[0].getPerson().getNickName());
            label2.setText(randomPlayers[1].getPerson().getNickName());
            label3.setText(randomPlayers[2].getPerson().getNickName());
            label4.setText(randomPlayers[3].getPerson().getNickName());
            label5.setText(randomPlayers[4].getPerson().getNickName());
            label6.setText(randomPlayers[5].getPerson().getNickName());
            label7.setText(randomPlayers[6].getPerson().getNickName());
            label8.setText(randomPlayers[7].getPerson().getNickName());
            MatchSolo matchSolo;
            for (int i = 0; i < randomPlayers.length; i+=2) {
                matchSolo = new MatchSolo(randomPlayers[i], randomPlayers[i+1],
                        dateQuarterFinal.getValue(), null, tournament);
                MatchSoloController matchSoloController = new MatchSoloController();
                matchSoloController.createMatchSoloPlayer(matchSolo);
                tournament.addMatch(matchSolo);
            }
            TournamentController tournamentController = new TournamentController();
            tournamentController.update(tournament);
        }
    }

    @FXML
    protected void handleStartSecondRoundButtonAction(ActionEvent e) {
        Random random = new Random();
        int index;
        ObservableList<Player> selectedPlayers = playerTable.getSelectionModel().getSelectedItems();
        List<Player> players = new ArrayList<>();
        Player[] randomPlayers = new Player[8];
        for (int i = 0; i < selectedPlayers.size(); i++) {
            do {
                index = random.nextInt(8);
            } while (randomPlayers[index] != null);
            randomPlayers[index] = selectedPlayers.get(i);
            switch (index) {
                case 0:
                    label1.setText(selectedPlayers.get(i).getPerson().getNickName());
                    break;
                case 1:
                    label2.setText(selectedPlayers.get(i).getPerson().getNickName());
                    break;
                case 2:
                    label3.setText(selectedPlayers.get(i).getPerson().getNickName());
                    break;
                case 3:
                    label4.setText(selectedPlayers.get(i).getPerson().getNickName());
                    break;
                case 4:
                    label5.setText(selectedPlayers.get(i).getPerson().getNickName());
                    break;
                case 5:
                    label6.setText(selectedPlayers.get(i).getPerson().getNickName());
                    break;
                case 6:
                    label7.setText(selectedPlayers.get(i).getPerson().getNickName());
                    break;
                case 7:
                    label8.setText(selectedPlayers.get(i).getPerson().getNickName());
                    break;
            }
        }
    }

    @FXML
    protected void handleStartThirdRoundButtonAction(ActionEvent e) {
        Random random = new Random();
        int index;
        ObservableList<Player> selectedPlayers = playerTable.getSelectionModel().getSelectedItems();
        List<Player> players = new ArrayList<>();
        Player[] randomPlayers = new Player[8];
        for (int i = 0; i < selectedPlayers.size(); i++) {
            do {
                index = random.nextInt(8);
            } while (randomPlayers[index] != null);
            randomPlayers[index] = selectedPlayers.get(i);
            switch (index) {
                case 0:
                    label1.setText(selectedPlayers.get(i).getPerson().getNickName());
                    break;
                case 1:
                    label2.setText(selectedPlayers.get(i).getPerson().getNickName());
                    break;
                case 2:
                    label3.setText(selectedPlayers.get(i).getPerson().getNickName());
                    break;
                case 3:
                    label4.setText(selectedPlayers.get(i).getPerson().getNickName());
                    break;
                case 4:
                    label5.setText(selectedPlayers.get(i).getPerson().getNickName());
                    break;
                case 5:
                    label6.setText(selectedPlayers.get(i).getPerson().getNickName());
                    break;
                case 6:
                    label7.setText(selectedPlayers.get(i).getPerson().getNickName());
                    break;
                case 7:
                    label8.setText(selectedPlayers.get(i).getPerson().getNickName());
                    break;
            }
        }
    }

    @FXML
    protected void handleFinalFinishedButtonAction(ActionEvent e) {

    }

    @FXML
    protected void handleNewTournamentButtonAction(ActionEvent e) {
        if (gameCmb.getValue() == null) {
            // No game ...
        } else if (tournamentNameText.getText().isEmpty()) {
            // No name ...
        } else {
            TournamentController tournamentController = new TournamentController();
            tournament = new Tournament();
            tournament.setGame(gameCmb.getValue());
            tournament.setName(tournamentNameText.getText());
            if (tournamentController.save(tournament)) {
                tournamentTable.getItems().add(tournament);
            }
        }
    }

    @FXML
    protected void handleUpdateTournamentButtonAction(ActionEvent e) {
        if (gameCmb.getValue() == null) {
            // No game ...
        } else if (tournamentNameText.getText().isEmpty()) {
            // No name ...
        } else {
            TournamentController tournamentController = new TournamentController();
            tournament = new Tournament();
            tournament.setGame(gameCmb.getValue());
            tournament.setName(tournamentNameText.getText());
            if (tournamentController.save(tournament)) {
                tournamentTable.getItems().add(tournament);
            }
        }
    }

    @FXML
    protected void handleDeleteTournamentButtonAction(ActionEvent e) {
        if (gameCmb.getValue() == null) {
            // No game ...
        } else if (tournamentNameText.getText().isEmpty()) {
            // No name ...
        } else {
            TournamentController tournamentController = new TournamentController();
            tournament = new Tournament();
            tournament.setGame(gameCmb.getValue());
            tournament.setName(tournamentNameText.getText());
            if (tournamentController.save(tournament)) {
                tournamentTable.getItems().add(tournament);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

        ObservableList<Tournament> tournamentSelectedItems = tournamentTable.getSelectionModel().getSelectedItems();
        tournamentSelectedItems.addListener(
            new ListChangeListener<Tournament>() {
                @Override
                public void onChanged(Change<? extends Tournament> change) {
                    tournament = tournamentTable.getSelectionModel().getSelectedItem();
                }
            }
        );

        ObservableList<Player> playerSelectedItems = playerTable.getSelectionModel().getSelectedItems();
        playerTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        playerSelectedItems.addListener(
            new ListChangeListener<Player>() {
                @Override
                public void onChanged(Change<? extends Player> change) {
//                    if (tournament != null && playerTable.getSelectionModel().getSelectedItems().size() == 8) {
                    if (playerTable.getSelectionModel().getSelectedItems().size() == 8) {
                        startFirstRoundButton.setDisable(false);
                    } else {
                        startFirstRoundButton.setDisable(true);
                    }
                }
            }
        );

    }
}
