package fr.isep.francois.projetapplication;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ProjetCreationController {

    private static Stage stage;

    @FXML
    private TextField nomprojet;

    @FXML
    private TextField budjet;

    @FXML
    private DatePicker datelimite;

    // Setter pour le stage
    public static void setStage(Stage stage) {
        ProjetCreationController.stage = stage;
    }

    // Constructeur
    public ProjetCreationController() {
    }
@FXML

private void creationprojet(){

    if (!nomprojet.getText().isEmpty() && !budjet.getText().isEmpty() && datelimite.getValue() != null) {
        try {
            // Conversion du budget de String à int
            int budget = Integer.parseInt(budjet.getText());

            // Création d'un objet Projet avec les données entrées
            Projet projet = new Projet(
                    nomprojet.getText(),                          // Nom du projet
                    new ArrayList<Tache>(),                       // Liste vide de tâches
                    datelimite.getValue(),                       // Date limite
                    new HashMap<Employe, String>(),              // Liste vide d'employés avec rôles
                    budget                                        // Budget du projet
            );

            // Affichage d'un message de confirmation
            System.out.println("Le projet a été créé : " + projet.getNom());

            projet.ajouterProjet(projet);
            OpenNewPageProjet();

        } catch (NumberFormatException e) {
            // Gestion de l'exception si le budget n'est pas un entier valide
            System.out.println("Erreur : Le budget doit être un nombre entier.");
        }
    }


}
    // Méthode pour ouvrir la page d'accueil
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
            // Charger la nouvelle page (hello-view.fxml)
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("Projet.fxml"));
            Scene scene = new Scene(loader.load(), 800, 600);
            stage.setTitle("Gestion Projet");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Le bouton est cliqué.");
    }
}
