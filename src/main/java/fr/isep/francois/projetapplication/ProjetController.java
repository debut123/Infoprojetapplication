package fr.isep.francois.projetapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjetController {

    private static Stage stage;  // Déclarer un champ Stage

    private static List<Projet> listeProjets;  // Liste des projets à afficher

    private Projet projetsaisi;



    @FXML
    private Button Acceuil; // Assurez-vous d'avoir une référence à ce bouton



    // Setter pour passer le Stage
    public void setStage(Stage stage) {
        this.stage = stage;
        System.out.println("Le stage est initialisé dans controleur:  " + this.stage);
    }

    public static void setListeProjets(List<Projet> listeProjets) {
        ProjetController.listeProjets = listeProjets;

    }

    public ProjetController() {
        // Initialisation de la liste des projets (exemple statique)

    }

    @FXML
    private ListView<HBox> listViewProjetsBox;

    // Méthode pour initialiser l'affichage de la liste des projets
    @FXML
    public void initialize() {
        // Récupérer la liste des projets existants depuis la classe Projet
        Map<Employe, String> employes = new HashMap<>();
        Projet projet2=new Projet("Projet A", new ArrayList<>(), LocalDate.now(), employes, 10000);




        // Effacer les éléments existants dans la ListView au démarrage
        listViewProjetsBox.getItems().clear();

        // Ajouter chaque projet à la ListView
        for (Projet projet : listeProjets) {
            // Créer un HBox pour chaque projet
            HBox hbox = new HBox();

            // Créer un label pour afficher le nom et le budget
            Label label = new Label("Nom: " + projet.getNom() + " | Budget: " + projet.getBudget());

            Button supresion=new Button("Suprimer");

            Button ouvrir=new Button("ouvrir");



            // Ajouter le label à l'HBox
            hbox.getChildren().addAll(label,supresion,ouvrir);

            // Ajouter l'HBox à la ListView
            listViewProjetsBox.getItems().add(hbox);
            supresion.setOnAction((ActionEvent e) -> {

                projet.supprimerProjet(projet.getNom());
                // Mise à jour de la ListView après la suppression
                listViewProjetsBox.getItems().remove(hbox);
                // Action à faire lorsque le bouton est cliqué
                //afficherDetailsEmploye(employe);
                listeProjets.remove(projet);
            });

            ouvrir.setOnAction((ActionEvent e) -> {

                ProjetGestionController projetGestionController=new ProjetGestionController();
                projetGestionController.setProjetreferent(projet);






                OpenNewPagegestionprojet(projet);

            });
        }
    }
    @FXML
    private void OpenNewPageaccueil() {

        System.out.println(stage);

        if (stage != null) {

            stage.setTitle("Nouvelle page");
            System.out.println("Page ouverte.");


        } else {
            System.out.println("Le stage est null");

        }

        try {


            // Charger la nouvelle page (Page2.fxml)
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(loader.load(), 400, 300);
            stage.setTitle("Hello!");
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