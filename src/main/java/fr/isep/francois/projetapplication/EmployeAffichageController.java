package fr.isep.francois.projetapplication;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;


public class EmployeAffichageController {



    private  static Stage stage;  // Déclarer un champ Stage



    private static Employe employe;

    public static void setProjet(Projet projet) {
        EmployeAffichageController.projet = projet;
    }

    private static Projet projet;

    public static void setEmploye(Employe employe) {
        EmployeAffichageController.employe = employe;
    }


    // Setter pour passer le Stage
    public void setStage(Stage stage) {
        this.stage = stage;
        System.out.println("Le stage est initialisé dans Hellocontroller:  "+this.stage);
    }


    @FXML
    Label nom;

    @FXML
    Label prenom;

    @FXML

    Label age;

    @FXML

    Label identifiant;

    @FXML

    Label type;

    @FXML
    Label dateembauche;

    @FXML
    ListView listedesprojets;






    public EmployeAffichageController() {
    }

    public void initialize(){

nom.setText("Nom : "+employe.getNom());
prenom.setText("Prénom : "+employe.getPrenom());
age.setText("âge : "+String.valueOf(employe.getAge()));
identifiant.setText("identifiant : "+String.valueOf(employe.getIdentifiant()));
type.setText("Profession : "+employe.getType());
dateembauche.setText("date embauche : "+String.valueOf( employe.getDate_embauche()));

listedesprojets.getItems().clear();
listedesprojets.getItems().addAll(employe.getListe_projet_realise());



    }

    @FXML
    private void ouverturepage(){

        if (projet==null){

            OpenNewPageEmploye();
        }
        else{
            OpenNewPagegestionprojet(projet);
            projet=null;

        }

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
            Scene scene = new Scene(loader.load(), 1200, 900);

            ProjetGestionController projetGestionController = loader.getController();

            // Mettre à jour le texte du label dans le contrôleur
            projetGestionController.setProjetreferent(projet);
            projetGestionController.informationname();

            stage.centerOnScreen();
            stage.setTitle("Gestion du Projet");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("le bouton est cliqué.");

    }


}
