package fr.isep.francois.projetapplication;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.ArrayList;

public class ProjetCreationTacheController {



    private static Stage stage;
    private static Projet projet;


    @FXML
    private TextField nom;

    @FXML
    private ComboBox<String> priorité;

    @FXML
    private TextArea description;


    public static void setStage(Stage stage) {
        ProjetCreationTacheController.stage = stage;
    }

    public static void setProjet(Projet projet) {
        ProjetCreationTacheController.projet = projet;
    }

    public ProjetCreationTacheController() {
    }

    @FXML
    private void creationtache() {
        // Vérifier si tous les champs sont remplis
        if (!nom.getText().isEmpty() && priorité.getValue() != null && !description.getText().isEmpty()) {
            // Créer une nouvelle tâche
            Tache tache = new Tache(nom.getText(), "en_cours", priorité.getValue(), description.getText(), new ArrayList<>());

            // Ajouter la tâche au projet
            projet.ajouterTache(tache);

            // Message de confirmation dans la console
            System.out.println("Tâche créée : " + tache.getNom());
        } else {
            // Afficher un message d'erreur si des champs sont vides
            System.out.println("Veuillez remplir tous les champs !");
        }
        retourProjet();
    }

    @FXML

    private void retourProjet(){

        OpenNewPagegestionprojet(projet);
    }

    @FXML
    private void OpenNewPagegestionprojet(Projet projet) {

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

            ProjetGestionController projetGestionController = loader.getController();

            // Mettre à jour le texte du label dans le contrôleur
            projetGestionController.setProjetreferent(projet);
            projetGestionController.informationname();


            stage.setTitle("Gestion du Projet");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("le bouton est cliqué.");

    }

}
