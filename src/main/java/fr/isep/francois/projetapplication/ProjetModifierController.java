package fr.isep.francois.projetapplication;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class ProjetModifierController {
    private static Stage stage;
    private static Projet projet;
    private ProjetController parentController;
    @FXML
    private Button enregistrement;
    @FXML
    private TextField nomChamps;
    @FXML
    private DatePicker dateLimiteChamps;
    @FXML
    private TextField budgetChamps;

    public static void setProjet(Projet projet) {
        ProjetModifierController.projet = projet;

    }
    public static void setStage(Stage stage) {
        ProjetModifierController.stage = stage;
    }

    @FXML                                                           // bouton annuler les modifications
    private void annulerModifications() {
        showAlert(Alert.AlertType.INFORMATION, "Annulation", "La tentative de modification du projet a Ã©tÃ© annulÃ©.");
        OpenNewPageProjet();
    }

    @FXML                                                           // Action Ã  executer pour le bouton modifier
    private void projetModifier() {
        String nom = nomChamps.getText();
        LocalDate dateLimite = dateLimiteChamps.getValue();
        String budget = budgetChamps.getText();

        if (nom.isEmpty() || dateLimite == null) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Remplissez les champs obligatoires, Ã  savoir date limite et nom");
        } else {
            projet.setNom(nom);
            //projet.setPrenom(prenom);
            projet.setBudget(Integer.parseInt(budget));
            //employe.setType(type);
            projet.setDate_limite(dateLimite);

            /*showAlert(Alert.AlertType.INFORMATION, "Enregistrement",
                    "Les informations du projet "
                            + projet.getNom() + " ont Ã©tÃ© modifiÃ©es avec succÃ¨s.");*/

            OpenNewPageProjet();
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void OpenNewPageProjet() {

        System.out.println(stage);

        if (stage != null) {

            stage.setTitle("Nouvelle page");
            System.out.println("Page ouverte.");


        } else {
            System.out.println("Le stage est null");

        }

        try {
            // Charger la nouvelle page (Page2.fxml)
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("Projet.fxml"));
            Scene scene = new Scene(loader.load(), 800, 600);

            stage.setTitle("Gestion de Projet");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("le bouton est cliqué.");
    }

    @FXML
    public void setParentController(ProjetController parentController) {
        this.parentController = parentController;                           // Setter pour la rÃ©fÃ©rence du contrÃ´leur parent
    }

    @FXML
    public void initialize() {

        nomChamps.setText(projet.getNom());
        //prenomChamps.setText(employe.getPrenom());
        budgetChamps.setText(String.valueOf(projet.getBudget()));
        //typeChamps.setText(employe.getType());
        dateLimiteChamps.setValue(projet.getDate_limite());


    }
}

