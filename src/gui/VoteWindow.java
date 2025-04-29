package gui;

import Characters.Candidate;
import Characters.Record;
import Characters.Voter;
import Characters.VoterVisitorFromEarth;
import Elections.Elections;
import Elections.FindWinner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Tato trieda predstavuje okno na hlasovanie.
 */
public class VoteWindow extends Application {
    private final TextArea resultsArea;
    private Elections elections;
    private ElectionsGUI electionsGUIInstance;

    /**
     * Konstruktor pre VoteWindow.
     * @param resultsArea TextArea pre vysledky.
     * @param elections Instancia volieb.
     * @param electionsGUIInstance Instancia hlavneho GUI pre volieb.
     */
    public VoteWindow(TextArea resultsArea, Elections elections, ElectionsGUI electionsGUIInstance) {
        this.resultsArea = resultsArea;
        this.elections = elections;
        this.electionsGUIInstance = electionsGUIInstance;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Vote Window");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(20);

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        gridPane.addRow(0, nameLabel, nameField);

        Label surnameLabel = new Label("Surname:");
        TextField surnameField = new TextField();
        gridPane.addRow(1, surnameLabel, surnameField);

        Label idLabel = new Label("ID:");
        TextField idField = new TextField();
        gridPane.addRow(2, idLabel, idField);

        Label moneyLabel = new Label("Money:");
        TextField moneyField = new TextField();
        gridPane.addRow(3, moneyLabel, moneyField);

        Label candidateLabel = new Label("Candidate:");
        TextField candidateField = new TextField();
        gridPane.addRow(4, candidateLabel, candidateField);

        Button voteButton = new Button("Vote");
        gridPane.add(voteButton, 4, 5);

        voteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String name = nameField.getText();
                String surname = surnameField.getText();
                String id = idField.getText();
                String money = moneyField.getText();
                String candidateName = candidateField.getText();

                if (!isValidCandidateName(candidateName)) {
                    System.err.println("Invalid input for voter: vote wasn't counted");
                    return;
                }

                Record record = new Record(name, surname, Integer.parseInt(id), Integer.parseInt(money));
                Voter voter = new VoterVisitorFromEarth(record);
                Candidate chosenCandidate = elections.findCandidateByName(candidateName);

                resultsArea.appendText(name + " " + surname + " ID: " + id + ", Money: " + money + " $$$ (Visitor from earth) is voting for: " + candidateName + "\n");

                if (chosenCandidate != null) {
                    chosenCandidate.increaseVote(voter);
                    elections.updateCandidateVotes();
                    electionsGUIInstance.updateCandidateVotes(); //updatujeme hlasy kandidatov
                    electionsGUIInstance.updateWinnerName();
                    clearFields(nameField, surnameField, idField, moneyField, candidateField);
                }

            }
        });

        Scene scene = new Scene(gridPane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Overi, ci je zadane meno kandidata platne.
     * @param candidateName Meno kandidata na overenie.
     * @return True, ak je meno platne, inak False.
     */
    private boolean isValidCandidateName(String candidateName) {
        return candidateName.equalsIgnoreCase("Exodus") || candidateName.equalsIgnoreCase("Celestrix") ||
                candidateName.equalsIgnoreCase("Nebuluna") || candidateName.equalsIgnoreCase("Primus") ||
                candidateName.equalsIgnoreCase("Zodiacalia");
    }
    private void clearFields(TextField... fields) {
        for (TextField field : fields) {
            field.clear();
        }
    }
}
