package fr.isep.francois.projetapplication;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class CalendrierController {

private  static Stage stage;

    public static void setStage(Stage stage) {
        CalendrierController.stage = stage;
    }


    public CalendrierController() {


    }
    @FXML
    private GridPane calendarGrid;  // Grille pour afficher les jours

    @FXML
    private Button prevMonthButton;  // Bouton pour le mois précédent
    @FXML
    private Button nextMonthButton;  // Bouton pour le mois suivant

    @FXML
    private HBox navigationBox; // Conteneur pour les boutons "Précédent" et "Suivant"

    private int currentYear = 2025;  // L'année actuelle
    private int currentMonth = 1;   // Le mois actuel (Janvier)

    private List<Projet> projetsList;  // Liste des projets

    @FXML
    private void initialize() {
        // Simuler des projets (vous pouvez obtenir cette liste depuis une base de données ou autre)


        projetsList = new ArrayList<>();

        for (Projet projet1:ProjetController.getListeProjets() ){


            projetsList.add(projet1);

        }





        /*projetsList.add(new Projet("Projet A", new ArrayList<>(), LocalDate.of(2025, 1, 15), null, 10000));
        projetsList.add(new Projet("Projet B", new ArrayList<>(), LocalDate.of(2025, 1, 25), null, 5000));
        projetsList.add(new Projet("Projet C", new ArrayList<>(), LocalDate.of(2025, 2, 10), null, 15000));


         */
        // Afficher les projets dans le mois actuel (par exemple janvier 2025)
        afficherProjetsDansCalendrier(currentYear, currentMonth);  // Exemple: afficher pour janvier 2025

        // Ajouter des actions pour les boutons de changement de mois
        prevMonthButton.setOnAction(e -> changerMois(-1));
        nextMonthButton.setOnAction(e -> changerMois(1));
    }

    // Méthode pour changer le mois
    private void changerMois(int increment) {
        currentMonth += increment;
        if (currentMonth < 1) {
            currentMonth = 12;
            currentYear--;
        } else if (currentMonth > 12) {
            currentMonth = 1;
            currentYear++;
        }
        afficherProjetsDansCalendrier(currentYear, currentMonth);  // Mettre à jour le calendrier
    }

    // Méthode pour afficher les projets dans la grille du calendrier
    private void afficherProjetsDansCalendrier(int annee, int mois) {
        // Remplir la grille avec les jours
        LocalDate premierJourDuMois = LocalDate.of(annee, mois, 1);
        int premierJourSemaine = premierJourDuMois.getDayOfWeek().getValue();  // Lundi = 1, Dimanche = 7
        int joursDansMois = premierJourDuMois.lengthOfMonth();

        // Vider la grille avant de remplir
        calendarGrid.getChildren().clear();

        // Afficher le titre du mois
        Label moisLabel = new Label(premierJourDuMois.getMonth().toString());
        moisLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18px; -fx-alignment: center;");
        calendarGrid.add(moisLabel, 0, 0, 7, 1);  // Placer le mois sur la première ligne

        // Afficher les jours de la semaine (Lun, Mar, Mer, etc.)
        String[] joursSemaine = {"Lun", "Mar", "Mer", "Jeu", "Ven", "Sam", "Dim"};
        for (int i = 0; i < joursSemaine.length; i++) {
            Label dayLabel = new Label(joursSemaine[i]);
            dayLabel.setStyle("-fx-font-weight: bold; -fx-alignment: center;");
            calendarGrid.add(dayLabel, i, 1);  // Placer les jours dans la deuxième ligne
        }

        // Remplir les jours du mois dans la grille
        for (int i = 1; i <= joursDansMois; i++) {
            int row = (i + premierJourSemaine - 1) / 7 + 2;  // Ajouter 2 pour ne pas écraser les lignes des titres
            int column = (i + premierJourSemaine - 1) % 7;

            // Créer un label pour chaque jour
            Label dayLabel = new Label(String.valueOf(i));
            dayLabel.setStyle("-fx-font-size: 14px; -fx-alignment: center;");
            dayLabel.setMaxWidth(Double.MAX_VALUE);  // Pour rendre le label cliquable sur toute la cellule

            // Créer un conteneur VBox pour afficher les projets sous chaque jour
            VBox dayBox = new VBox();
            dayBox.getChildren().add(dayLabel);

            // Ajouter une bordure autour du jour (définir une couleur de fond et une bordure)
            dayBox.setStyle("-fx-border-color: gray; -fx-border-width: 1px; -fx-padding: 5px;");

            // Créer une grille pour afficher les projets sous chaque jour
            GridPane projectGrid = new GridPane();
            projectGrid.setVgap(5);
            projectGrid.setHgap(5);

            // Afficher les projets correspondant à la date
            for (Projet projet : projetsList) {
                if (projet.getDate_limite().getDayOfMonth() == i && projet.getDate_limite().getMonthValue() == mois) {
                    Label projetLabel = new Label(projet.getNom());
                    projetLabel.setStyle("-fx-text-fill: red; -fx-font-size: 10px;");
                    projetLabel.setTooltip(new Tooltip("Date limite: " + projet.getDate_limite()));

                    // Ajouter le label du projet dans la grille de projets
                    projectGrid.add(projetLabel, 0, projectGrid.getRowCount());
                }
            }

            // Ajouter la grille des projets au conteneur VBox sous le jour
            dayBox.getChildren().add(projectGrid);

            // Ajouter le conteneur du jour à la grille principale
            calendarGrid.add(dayBox, column, row);
        }

        // Ajouter des bordures autour des jours (facultatif)
        for (int i = 0; i < calendarGrid.getChildren().size(); i++) {
            Node node = calendarGrid.getChildren().get(i);
            if (node instanceof VBox) {
                VBox vbox = (VBox) node;
                vbox.setStyle("-fx-border-color: lightgray; -fx-border-width: 1px;");
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
}
