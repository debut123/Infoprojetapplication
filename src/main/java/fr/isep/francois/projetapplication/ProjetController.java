package fr.isep.francois.projetapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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

    @FXML
    private CategoryAxis liste_nom_projet;  // Axe des noms des projets
    @FXML
    private NumberAxis liste_budjet;        // Axe des budgets

    @FXML
    private BarChart<String, Number> budgetBarChart;  // Le graphique

    private List<Projet> projetsList;  // Liste des projets à afficher




    // Setter pour passer le Stage
    public void setStage(Stage stage) {
        this.stage = stage;
        System.out.println("Le stage est initialisé dans controleur:  " + this.stage);
    }

    public static void setListeProjets(List<Projet> listeProjets) {
        ProjetController.listeProjets = listeProjets;

    }
    public static List<Projet> getListeProjets() {
        return listeProjets;
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

            Button modifier=new Button("Modifier");



            // Ajouter le label à l'HBox
            hbox.getChildren().addAll(label,supresion,ouvrir,modifier);

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

            modifier.setOnAction((ActionEvent e) -> {

                OpenNewPagemodifierprojet(projet);
            });

            ouvrir.setOnAction((ActionEvent e) -> {

                ProjetGestionController projetGestionController=new ProjetGestionController();
                projetGestionController.setProjetreferent(projet);






                OpenNewPagegestionprojet(projet);

            });
        }

        // Vérifier si l'élément graphique a bien été injecté
        if (budgetBarChart == null) {
            System.err.println("Erreur : budgetBarChart n'a pas été correctement injecté.");
            return;
        }

        if (listeProjets!=null) {
// Créer une série de données pour le graphique
    XYChart.Series<String, Number> series = new XYChart.Series<>();
    series.setName("Projets");

    // Ajouter les données des projets dans la série
    for (Projet projet : listeProjets) {
        // Ajouter un projet à la série (nom du projet, budget)
        series.getData().add(new XYChart.Data<>(projet.getNom(), projet.getBudget()));
    }

    // Ajouter la série au graphique
    budgetBarChart.getData().add(series);


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
    private void OpenNewPagecreationprojet() {

        System.out.println(stage);

        if (stage != null) {

            stage.setTitle("Nouvelle page");
            System.out.println("Page ouverte.");


        } else {
            System.out.println("Le stage est null");

        }

        try {


            // Charger la nouvelle page (Page2.fxml)
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("creationprojet.fxml"));
            Scene scene = new Scene(loader.load(), 800, 600);
            stage.setTitle("Creation d'un nouveau projet");
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
    @FXML
    private void OpenNewPagemodifierprojet(Projet projet) {

        //System.out.println(stage);

        if (stage != null) {

            stage.setTitle("Nouvelle page");
            System.out.println("Page ouverte.");


        } else {
            System.out.println("Le stage est null");

        }

        try {
            ProjetModifierController.setProjet(projet);

            // Charger la nouvelle page (Page2.fxml)
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("projetModifier.fxml"));
            ProjetModifierController.setProjet(projet);
            Scene scene = new Scene(loader.load(), 800, 600);

            ProjetModifierController modifyProjController = loader.getController();
            modifyProjController.setParentController(this);

            stage.setTitle("Modifier projet");
            stage.setScene(scene);
            stage.show();

            modifyProjController.initialize();

        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("le bouton est cliqué.");

    }



}