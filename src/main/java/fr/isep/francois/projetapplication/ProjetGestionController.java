package fr.isep.francois.projetapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
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
                Button modifierTache2 = new Button("Modifier");
                Button boutonSuppression = new Button("Supprimer");
                Button affectation=new Button("affecter des employés:");
                Button affichage=new Button("information");

                CheckBox etat=new CheckBox("état");

                etat.setOnAction(e -> {
                    if (etat.isSelected()) {
                        if (tache.getEtat() == "en_cours") {
                            tache.setEtat("Validé");

                            // Action lorsque la case est cochée
                        } else if (tache.getEtat() == "à faire") {

                            tache.setEtat("Validé");

                        }

                        else{

                            tache.setEtat("Validé");
                        }
                        System.out.println("La tache est "+tache.getEtat());

                    } else {
                        //System.out.println("La ta");
                        // Action lorsque la case n'est pas cochée
                    }
                });


                // Ajouter un événement pour le bouton de suppression
                affectation.setOnAction(event -> {

                    OpenNewPageAffectation(tache);
                });
                affichage.setOnAction(event -> {
                    TacheAffichageController.setProjet(projetreferent);
                    OpenNewPageaffichageTache(tache);

                });

                boutonSuppression.setOnAction(event -> {

                    tache.supprimerTache(projetreferent.getListe_tache_projet(),tache);
                    initialize();
                });

                modifierTache2.setOnAction(event -> {
                    OpenNewPagemodifierTache(tache);
                });

                // Ajouter le label et le bouton à la HBox
                hbox.getChildren().addAll(labelTache, boutonSuppression,affectation,affichage,etat);

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
            EmployeAffichageController.setProjet(projetreferent);
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("employeaffichage.fxml"));
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
    private void OpenNewPageaffichageTache(Tache tache) {

        //System.out.println(stage);

        if (stage != null) {

            stage.setTitle("Nouvelle page");
            System.out.println("Page ouverte.");


        } else {
            System.out.println("Le stage est null");

        }

        try {


            // Charger la nouvelle page (Page2.fxml)
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("tacheaffichage.fxml"));
            TacheAffichageController.setTache(tache);
            Scene scene = new Scene(loader.load(), 800, 600);
            stage.setTitle("Tache");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("le bouton est cliqué.");

    }
    @FXML
    private void OpenNewPagemodifierTache(Tache tache) {

        //System.out.println(stage);

        if (stage != null) {

            stage.setTitle("Nouvelle page");
            System.out.println("Page ouverte.");


        } else {
            System.out.println("Le stage est null");

        }

        try {


            // Charger la nouvelle page (Page2.fxml)
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("tacheModifier.fxml"));
            TacheAffichageController.setTache(tache);
            Scene scene = new Scene(loader.load(), 800, 600);
            stage.setTitle("Tache");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("le bouton est cliqué.");

    }




    @FXML
    public void ecrireCSV_rapport(ActionEvent actionEvent) {
        try (FileWriter fch = new FileWriter("rapport_projets.csv")) { // Nom de fichier CSV
            // Définition du format CSV avec un en-tête
            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader(new String[]{"Nom du Projet", "Date Limite", "Budget", "Employé", "Rôle"})
                    .build();

            // Création du CSVPrinter pour écrire dans le fichier
            CSVPrinter printer = new CSVPrinter(fch, csvFormat);

            // Vérifier si la liste des projets contient des éléments
            System.out.println("Liste des projets: " + projetreferent.getListeProjets().size());

            for (Projet projet : projetreferent.getListeProjets()) {
                System.out.println("Nom du projet: " + projet.getNom()); // Afficher le nom du projet
                String nomProjet = projet.getNom();
                String dateLimite = projet.getDate_limite().toString(); // Formater la date selon vos besoins
                int budget = projet.getBudget();

                // Vérifier si la liste des employés est correcte
                System.out.println("Nombre d'employés dans le projet: " + projet.getListe2_employe_projet().size());

                for (Map.Entry<Employe, String> entry : projet.getListe2_employe_projet().entrySet()) {
                    Employe employe = entry.getKey();
                    String role = entry.getValue();

                    System.out.println("Employé: " + employe.getNom() + " - Rôle: " + role); // Afficher les informations sur l'employé

                    // Écrire dans le fichier CSV

                }
                printer.printRecord("nom : "+nomProjet+" date limite: "+dateLimite+" Budjet : "+budget);
                //printer.printRecord("test de printer.");
            }

            // Fermer le printer une fois l'écriture terminée
            printer.flush();
            System.out.println("Le rapport a été écrit avec succès dans le fichier CSV.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'écriture dans le fichier CSV.");
        }
    }

}
