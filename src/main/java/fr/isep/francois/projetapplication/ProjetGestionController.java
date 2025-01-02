package fr.isep.francois.projetapplication;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ProjetGestionController {


    private static Stage stage;  // Déclarer un champ Stage
    private static Projet projetreferent;

    public static void setProjetreferent(Projet projetreferent) {
        ProjetGestionController.projetreferent = projetreferent;

    }

    @FXML
    private Button Acceuil;

    @FXML
    private Button ajoutTache;

    @FXML
    private Label information;
    @FXML
    private ListView<HBox> listViewEmployes;  // Liste pour afficher les employés

    @FXML
    private ListView<HBox> listViewTaches;  // Liste pour afficher les tâches


    public void setInformation(Label information) {
        this.information = information;
    }
    @FXML
    public void informationname() {


        if (information != null) {
            information.setText("Nom du projet :"+ projetreferent.getNom()); // Met à jour le texte du label
            System.out.println("information est mis à jour");
        } else {
            System.out.println("Le label 'information' est nul");
        }
    }



    // Setter pour passer le Stage
    public void setStage(Stage stage) {
        this.stage = stage;
        System.out.println("Le stage est initialisé dans controleur:  " + this.stage);
    }

    public ProjetGestionController() {


    }

    public void initialize() {
        if (projetreferent != null) {


            information.setText("Nom du projet: " + projetreferent.getNom());


            afficherEmployes();
            afficherTaches();

        }


    }
    // Méthode pour afficher les employés dans la ListView
    private void afficherEmployes() {


        if (projetreferent != null && projetreferent.getListe2_employe_projet() != null) {
            listViewEmployes.getItems().clear();  // Effacer les anciens éléments
            for (Map.Entry<Employe, String> entry : projetreferent.getListe2_employe_projet().entrySet()) {
                Employe employe = entry.getKey();
                String role = entry.getValue();

                // Créer une HBox pour l'employé
                HBox hbox = new HBox();
                Label labelEmploye = new Label(employe.getNom() + " " + employe.getPrenom() + " - Rôle: " + role +" ");
                Button boutonSuppression = new Button("Supprimer");

                Button information=new Button("information:");

                // Ajouter un événement pour le bouton de suppression
                boutonSuppression.setOnAction(event -> {projetreferent.supprimerEmploye(employe);

                        initialize();}
                );
                // Ajouter un événement pour le bouton de suppression
                information.setOnAction(event -> {
                    EmployeAffichageController.setEmploye(employe);
                    OpenNewPageEmployeaffichage();

                        }
                );

                // Ajouter le label et le bouton à la HBox
                hbox.getChildren().addAll(labelEmploye, boutonSuppression,information);

                listViewEmployes.getItems().add(hbox);
                // Ajouter la HBox à la ListView des employés
            }
        }
    }
    // Méthode pour afficher les tâches dans la ListView
    private void afficherTaches() {
        if (projetreferent != null && projetreferent.getListe_tache_projet() != null) {
            listViewTaches.getItems().clear();  // Effacer les anciens éléments
            for (Tache tache : projetreferent.getListe_tache_projet()) {

                HBox hbox = new HBox();
                Label labelTache = new Label(tache.getNom() + " - " + tache.getDescription());
                Button boutonSuppression = new Button("Supprimer");


                Button affectation=new Button("affecter des employés:");

                // Ajouter un événement pour le bouton de suppression
                affectation.setOnAction(event -> {

                    OpenNewPageAffectation(tache);
                });
                boutonSuppression.setOnAction(event -> {

                    tache.supprimerTache(projetreferent.getListe_tache_projet(),tache);
                    initialize();
                });

                // Ajouter le label et le bouton à la HBox
                hbox.getChildren().addAll(labelTache, boutonSuppression,affectation);

                // Ajouter la HBox à la ListView des tâches
                listViewTaches.getItems().add(hbox);




            }
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
    private void OpenNewPageajoutemploye() {

        System.out.println(stage);

        if (stage != null) {

            stage.setTitle("Nouvelle page");
            System.out.println("Page ouverte.");


        } else {
            System.out.println("Le stage est null");

        }

        try {


            // Charger la nouvelle page (Page2.fxml)
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("projetajouteremploye.fxml"));
            Projetajoutemploye projetajoutemploye=loader.getController();


            System.out.println("Contrôleur récupéré : " + projetajoutemploye);

            Projetajoutemploye.setProjetreferent(projetreferent);

            Scene scene = new Scene(loader.load(), 800, 600);


            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("le bouton est cliqué.");

    }
    @FXML
    private void OpenNewPageAffectation(Tache tache) {

        System.out.println(stage);

        if (stage != null) {

            stage.setTitle("Nouvelle page");
            System.out.println("Page ouverte.");


        } else {
            System.out.println("Le stage est null");

        }

        try {


            // Charger la nouvelle page (Page2.fxml)
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("affectationemployetache.fxml"));

            ProjetAffectattionEmployeTache projetAffectattionEmployeTache = loader.getController();

            // Mettre à jour le texte du label dans le contrôleur
            projetAffectattionEmployeTache.setProjetreferent(projetreferent);
            projetAffectattionEmployeTache.setTache(tache);



            Scene scene = new Scene(loader.load(), 800, 600);



            stage.setTitle("Page de controle");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("le bouton est cliqué.");

    }
    @FXML
    private void OpenNewPageCreationtache() {

        System.out.println(stage);

        if (stage != null) {

            stage.setTitle("Nouvelle page");
            System.out.println("Page ouverte.");


        } else {
            System.out.println("Le stage est null");

        }

        try {


            // Charger la nouvelle page (Page2.fxml)
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("creationtache.fxml"));

            ProjetCreationTacheController projetCreationTacheController= loader.getController();

            // Mettre à jour le texte du label dans le contrôleur
            projetCreationTacheController.setProjet(projetreferent);




            Scene scene = new Scene(loader.load(), 800, 600);



            stage.setTitle("Page de controle");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("le bouton est cliqué.");

    }
    @FXML
    private void OpenNewPageEmployeaffichage() {

        System.out.println(stage);

        if (stage != null) {

            stage.setTitle("Nouvelle page");
            System.out.println("Page ouverte.");


        } else {
            System.out.println("Le stage est null");

        }

        try {


            // Charger la nouvelle page (Page2.fxml)
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("employeaffichage.fxml"));
            Scene scene = new Scene(loader.load(), 800, 600);
            stage.setTitle("Gestion des emplyés");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("le bouton est cliqué.");

    }




}
