package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import Characters.*;
import Elections.*;
import Elections.FindWinner;

/**
 * Táto trieda predstavuje GUI pre volby.
 */
public class ElectionsGUI extends Application {
    // Deklaracia komponentov GUI
    private final Button startElection = new Button("Start Election");
    private final Button clear = new Button("Clear Results");
    private final Button voteWindowButton = new Button("Vote");

    private final TextField type1VotersLowerField = new TextField();
    private final TextField type1VotersHigherField = new TextField();
    private final TextField type2VotersLowerField = new TextField();
    private final TextField type2VotersHigherField = new TextField();
    private final TextField type3VotersLowerField = new TextField();
    private final TextField type3VotersHigherField = new TextField();

    private final Label type1VotersLowerLabel = new Label("Lower Type 1 Voters:");
    private final Label type1VotersHigherLabel = new Label("Higher Type 1 Voters:");
    private final Label type2VotersLowerLabel = new Label("Lower Type 2 Voters:");
    private final Label type2VotersHigherLabel = new Label("Higher Type 2 Voters:");
    private final Label type3VotersLowerLabel = new Label("Lower Type 3 Voters:");
    private final Label type3VotersHigherLabel = new Label("Higher Type 3 Voters:");

    private final TextArea resultsArea = new TextArea();

    private final TextField[] candidateVotesFields = new TextField[5];
    private final Label[] candidateLabels = new Label[5];

    private final Label electionswinnerLabel = new Label("Winner:");
    private final TextField winnerNameField = new TextField();

    private Elections elections = new Elections();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Elections");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(20);

        gridPane.addRow(0, type1VotersLowerLabel, type1VotersLowerField);
        gridPane.addRow(1, type1VotersHigherLabel, type1VotersHigherField);
        gridPane.addRow(2, type2VotersLowerLabel, type2VotersLowerField);
        gridPane.addRow(3, type2VotersHigherLabel, type2VotersHigherField);
        gridPane.addRow(4, type3VotersLowerLabel, type3VotersLowerField);
        gridPane.addRow(5, type3VotersHigherLabel, type3VotersHigherField);

        gridPane.addRow(6, startElection, clear);
        gridPane.add(resultsArea, 4, 4, 4, 4);

        for (int i = 0; i < 5; i++) {
            candidateLabels[i] = new Label(getCandidateLabel(i));
            candidateVotesFields[i] = new TextField();
            candidateVotesFields[i].setEditable(false);
            gridPane.addRow(i+3+5, candidateLabels[i], candidateVotesFields[i]);
        }

        gridPane.addRow(13, electionswinnerLabel, winnerNameField);

        gridPane.add(voteWindowButton, 2, 6);

        startElection.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleStartElection();
            }
        });

        clear.setOnAction(e -> {
            resultsArea.clear();
            clearCandidateVotes();
            electionswinnerLabel.setText("Winner: ");
            winnerNameField.clear();
        });

        voteWindowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openVoteWindow(primaryStage);
            }
        });

        Scene scene = new Scene(gridPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Ziska popis kandidata na zaklade indexu.
     *
     * @param index Index kandidata.
     * @return Popis kandidata.
     */
    private String getCandidateLabel(int index) {
        switch (index) {
            case 0:
                return "Exodus Votes:";
            case 1:
                return "Celestrix Votes:";
            case 2:
                return "Nebuluna Votes:";
            case 3:
                return "Primus Votes:";
            default:
                return "Zodiacalia Votes:";
        }
    }

    /**
     * Spracovava akciu pri spusteni volieb.
     */
    private void handleStartElection() {
        try {
            int nVoters1Lower = Integer.parseInt(type1VotersLowerField.getText());
            int nVoters1Higher = Integer.parseInt(type1VotersHigherField.getText());
            int nVoters2Lower = Integer.parseInt(type2VotersLowerField.getText());
            int nVoters2Higher = Integer.parseInt(type2VotersHigherField.getText());
            int nVoters3Lower = Integer.parseInt(type3VotersLowerField.getText());
            int nVoters3Higher = Integer.parseInt(type3VotersHigherField.getText());

            if (nVoters1Lower < 0 || nVoters1Higher < 0 || nVoters2Lower < 0 || nVoters2Higher < 0 || nVoters3Lower < 0 || nVoters3Higher < 0) {
                throw new InvalidInputException("Invalid input: negative values");// odvodena vynimka je vyuzivana tu
            }

            resultsArea.clear();
            elections.runElection(nVoters1Lower, nVoters1Higher, nVoters2Lower, nVoters2Higher, nVoters3Lower, nVoters3Higher, resultsArea);
            updateCandidateVotes();
        } catch (NumberFormatException e) {
            System.err.println("Invalid number: Missing number");
        } catch (InvalidInputException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Otvori okno pre hlasovanie.
     *
     * @param primaryStage Hlavne okno aplikacie.
     */
    private void openVoteWindow(Stage primaryStage) {
        VoteWindow voteWindow = new VoteWindow(resultsArea, elections, this);
        Stage voteStage = new Stage();
        voteWindow.start(voteStage);
    }

    /**
     * Aktualizuje pocty hlasov pre kandidatov.
     */
    void updateCandidateVotes() {
        int[] candidateVotes = elections.getCandidateVotes(); //
        for (int i = 0; i < 5; i++) {
            candidateVotesFields[i].setText(String.valueOf(candidateVotes[i]));
        }
        String winnerName = elections.getWinnerName();
        winnerNameField.setText(winnerName);
    }

    /**
     * Vycistí pocty hlasov pre kandidatov.
     */
    private void clearCandidateVotes() {
        for (int i = 0; i < 5; i++) {
            candidateVotesFields[i].clear();
        }
    }

    /**
     * Aktualizuje meno vitaza.
     */
    public void updateWinnerName() {
        String winnerName = elections.getWinnerName();
        electionswinnerLabel.setText("Winner:");
        winnerNameField.setText(winnerName);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
