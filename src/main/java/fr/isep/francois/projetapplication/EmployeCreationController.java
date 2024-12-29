package fr.isep.francois.projetapplication;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.ArrayList;

public class EmployeCreationController {

    private  static Stage stage;  // Déclarer un champ Stage

    private EmployeController parentController;

    // Setter pour la référence du contrôleur parent
    public void setParentController(EmployeController parentController) {
        this.parentController = parentController;

    }

    @FXML
    private Button enregistrement;

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField age;
    @FXML
    private TextField type;
    @FXML
    private TextField identifiant;


    @FXML
    protected void nouvelemploye() {
        //welcomeText.setText("Test réussi!");



        String nomEmploye = nom.getText();
        String prenomEmploye = prenom.getText();
        String ageEmploye = age.getText();
        String typeEmploye = type.getText();
        String identifiantEmploye = identifiant.getText();

        // Vérifier si les champs sont vides
        if (nomEmploye.isEmpty() || prenomEmploye.isEmpty() || ageEmploye.isEmpty() ||
                typeEmploye.isEmpty() || identifiantEmploye.isEmpty()) {
            // Afficher un message d'erreur si un champ est vide
            System.out.println("Erreur Tous les champs doivent être remplis !");
            return;
        }
        // Créer un nouvel employé
        try {
            int id = Integer.parseInt(identifiantEmploye);
            int ageInt = Integer.parseInt(ageEmploye);

            // Créer un nouvel employé
            Employe nouvelEmploye = new Employe(id, nomEmploye, prenomEmploye, ageInt, typeEmploye, new ArrayList<>(), null);
            nouvelEmploye.ajouter_employe(id, nomEmploye, prenomEmploye, ageInt, typeEmploye, new ArrayList<>(), null);

            if (parentController != null) {

                parentController.updateEmployeeList();
            }
            OpenNewPageEmploye();

        } catch (NumberFormatException e) {
            System.out.println("exeption erreur l'identifiant ou l'age n'est pas un entier.");
            //showAlert("Erreur", "L'âge et l'identifiant doivent être des nombres !");
        }







    }


    // Setter pour passer le Stage
    public void setStage(Stage stage) {
        this.stage = stage;
        System.out.println("Le stage est initialisé dans controlle:  "+this.stage);
    }

    public EmployeCreationController() {
    }

    @FXML
    private void OpenNewPageEmploye() {

        System.out.println(stage);

        if (stage != null) {

            stage.setTitle("Nouvelle page");
            System.out.println("Page ouverte.");


        } else {
            System.out.println("Le stage est null");

        }

        try {


            // Charger la nouvelle page (Page2.fxml)
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("employe.fxml"));
            Scene scene = new Scene(loader.load(), 800, 600);



            stage.setTitle("Gestion des employés");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("le bouton est cliqué.");

    }

}
