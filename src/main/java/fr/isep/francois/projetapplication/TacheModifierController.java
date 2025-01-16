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
    private TextField nom;
    @FXML
    private ComboBox<String> priorite;
    @FXML
    private TextArea description;

    public static void setStage(Stage stage) {
        TacheModifierController.stage = stage;
    }

    public static void setProjet(Projet projet) {
        TacheModifierController.projet = projet;
    }

    public static void setTache(Tache tache) {
        TacheModifierController.tache = tache;
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
        // Vérifiez d'abord que les éléments ne sont pas nuls
        if (nom != null && priorite != null && description != null) {
            String nomText = nom.getText();
            String prioriteValue = priorite.getValue();
            String descriptionText = description.getText();

            // Vérifiez si les champs ne sont pas vides
            if (!nomText.isEmpty() && prioriteValue != null && !descriptionText.isEmpty()) {
                tache.setNom(nomText);
                tache.setPriorite(prioriteValue);
                tache.setDescription(descriptionText);

                OpenNewPageTache();
            } else {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Remplissez tous les champs obligatoires.");
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Un ou plusieurs champs sont manquants.");
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
