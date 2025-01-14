package fr.isep.francois.projetapplication;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;


public class TacheAffichageController {


    private  static Stage stage;  // Déclarer un champ Stage


    private static Projet projet;
    private static Tache tache;

    @FXML
    private Label nom;

    @FXML
    private Label etat;

    @FXML
    private Label priorite;

    @FXML
    private Label description;

    @FXML
    private ListView listeemploye;



    public static void setProjet(Projet projet) {
        TacheAffichageController.projet = projet;
    }

    public static void setStage(Stage stage) {
        TacheAffichageController.stage = stage;
    }

    public static void setTache(Tache tache) {
        TacheAffichageController.tache = tache;
    }

    public TacheAffichageController() {
    }

    public void initialize(){

        nom.setText("Nom : "+tache.getNom());
        etat.setText("état: "+tache.getEtat());
        priorite.setText("Priorité: "+tache.getPriorite());
        description.setText("Description : "+tache.getDescription());
        listeemploye.getItems().clear();
        for (Employe employe: tache.getListeEmployeProjet()){

            listeemploye.getItems().add(employe.getNom());
        }




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

            ProjetGestionController.setProjetreferent(projet);
            // Charger la nouvelle page (Page2.fxml)
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("ProgetGestion.fxml"));
            Scene scene = new Scene(loader.load(), 1200, 900);
            stage.centerOnScreen();
            stage.setTitle("Gestion des employés");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("le bouton est cliqué.");

    }


}
