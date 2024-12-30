package fr.isep.francois.projetapplication;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public class ProjetAffectattionEmployeTache {

    private  static Stage stage;  // Déclarer un champ Stage
    private static Projet projetreferent;


    private static Tache tache;

    public static void setTache(Tache tache) {
        ProjetAffectattionEmployeTache.tache = tache;
    }

    @FXML
    private Label titre;

    @FXML
    private ListView<HBox> listViewEmployes;  // Liste pour afficher les employés


    public static void setProjetreferent(Projet projetreferent) {
        ProjetAffectattionEmployeTache.projetreferent = projetreferent;
        System.out.println("Le projet référent est chargé "+projetreferent.getNom());

    }
    public void initialize() {
        // Vérifiez si le label 'titre' est null
        if (titre != null) {
            titre.setText("Gestion de la Tache: ");
        } else {
            System.out.println("Le label 'titre' est null !");
        }
        afficherEmployes();


    }



    // Setter pour passer le Stage
    public void setStage(Stage stage) {
        this.stage = stage;
        System.out.println("Le stage est initialisé dans Hellocontroller:  "+this.stage);
    }

    public ProjetAffectattionEmployeTache() {

    }
    @FXML
    Button acceuil;

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
                Button affectation=new Button("affecter");

                if (tache.getListeEmployeProjet().contains(employe)){


                    affectation.setText("Désafecter");

                    affectation.setOnAction(event -> {tache.desaffecterTache(employe);
                        System.out.println(employe.getNom()+" a été désaffecter de  "+tache.getNom());

                        afficherEmployes();}
                    );


                }
                else {


                    // Ajouter un événement pour le bouton de suppression
                    affectation.setOnAction(event -> {tache.attribuerTache(employe);
                        System.out.println(employe.getNom()+" a été affecter a  "+tache.getNom());

                        afficherEmployes();}
                    );


                }



                // Ajouter le label et le bouton à la HBox
                hbox.getChildren().addAll(labelEmploye,affectation);

                listViewEmployes.getItems().add(hbox);
                // Ajouter la HBox à la ListView des employés
            }
        }
    }

    @FXML
    private void OpenNewPage() {

        System.out.println(stage);

        if (stage != null) {

            stage.setTitle("Nouvelle page");
            System.out.println("Page ouverte.");


        } else {
            System.out.println("Le stage est null");

        }

        try {


            // Charger la nouvelle page (Page2.fxml)
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("controle.fxml"));
            Scene scene = new Scene(loader.load(), 400, 300);
            stage.setTitle("Page de controle");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("le bouton est cliqué.");

    }

    @FXML
    public void retour(){

        OpenNewPagegestionprojet(projetreferent);
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
