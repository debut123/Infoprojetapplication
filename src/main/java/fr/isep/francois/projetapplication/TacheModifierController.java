package fr.isep.francois.projetapplication;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class TacheModifierController {
    private static Stage stage;
    private static Projet projet;
    private static Tache tache;
    @FXML
    private TextField nomChamps;
    @FXML
    private ComboBox<String> prioriteChamps;
    @FXML
    private TextArea descriptionChamps;

    public static void setStage(Stage stage) {
        TacheModifierController.stage = stage;
    }

    public static void setProjet(Projet projet) {
        TacheModifierController.projet = projet;
    }


    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML                                                           // bouton annuler les modifications
    private void annulerModifications() {
        showAlert(Alert.AlertType.INFORMATION, "Annulation", "La tentative de modification du projet a Ã©tÃ© annulÃ©.");
        OpenNewPageTache();
    }

    @FXML
    private void tacheModifier() {
        String nom = nomChamps.getText();
        String priorite = prioriteChamps.getValue();
        String description = descriptionChamps.getText();

        if (!nomChamps.getText().isEmpty() && prioriteChamps.getValue() != null && !descriptionChamps.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Remplissez tous les champs obligatoires.");
        }
        else {
            tache.setNom(nom);
            tache.setPriorite(priorite);
            tache.setDescription(description);

            OpenNewPageTache();
        }
    }

    @FXML
    private void OpenNewPageTache() {

        System.out.println(stage);

        if (stage != null) {

            stage.setTitle("Nouvelle page");
            System.out.println("Page ouverte.");


        } else {
            System.out.println("Le stage est null");

        }

        try {
            // Charger la nouvelle page (Page2.fxml)
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("ProgetGestion.fxml"));
            Scene scene = new Scene(loader.load(), 800, 600);

            stage.setTitle("Gestion de Projet");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("le bouton est cliqué.");
    }
}
