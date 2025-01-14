package fr.isep.francois.projetapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Projetajoutemploye {

    private  static Stage stage;  // Déclarer un champ Stage
    private static Projet projetreferent;

    @FXML
    private TextField role;

    @FXML
    private Label message;



    public static void setProjetreferent(Projet projetreferent) {
        Projetajoutemploye.projetreferent = projetreferent;
        System.out.println("Le projet referent est chargé sur la page "+projetreferent.getNom());
    }

    // Setter pour passer le Stage
    public void setStage(Stage stage) {
        this.stage = stage;
        System.out.println("Le stage est initialisé dans Hellocontroller:  "+this.stage);
    }


    public Projetajoutemploye() {
    }



    @FXML
    Button acceuil;

    @FXML
    private void retour(){

        OpenNewPagegestionprojet(projetreferent);
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
    public void initialize() {

        Employe employe=new Employe(1, "Dupont", "Jean", 30, "Développeur", new ArrayList<>(), LocalDate.of(2020, 1, 15));
        // Afficher les employés dans la ListView

        //System.out.println(employe.getListe_employe());
        afficherEmployes(employe.getListe_employe());
    }
    @FXML
    private ListView<HBox> listViewEmployes;  // Liste pour afficher les employés

    // Méthode pour afficher les employés dans la ListView
    private void afficherEmployes(List<Employe> listeEmployes) {

        listViewEmployes.getItems().clear();  // Effacer les anciens éléments

        // Pour chaque employé dans la liste
        for (Employe employe : listeEmployes) {

            if (!projetreferent.getListe2_employe_projet().containsKey(employe)) {
                // Créer une HBox pour cet employé
                HBox hbox = new HBox(10);  // Espacement de 10px entre les éléments
                hbox.setStyle("-fx-padding: 5; -fx-border-color: lightgray; -fx-border-width: 1;");

                // Créer un Label pour afficher le nom et le prénom
                Label labelEmploye = new Label(employe.getNom() + " " + employe.getPrenom());

                // Créer un bouton pour affecter/désaffecter un employé (facultatif)
                Button affectationButton = new Button("Ajouter");

                // Ajouter une action au bouton (ici, un simple print dans la console)
                affectationButton.setOnAction(event -> {

                    if (role.getText() == null || role.getText().trim().isEmpty()) {
                        message.setText("Veullez remplir un role");


                        //projetreferent.ajouterEmploye(employe, "indéfini");
                        //System.out.println(employe.getNom() + " " + employe.getPrenom() + " a été affecté.");
                        //afficherEmployes(employe.getListe_employe());
                    }
                    else {
                        message.setText("");
                        projetreferent.ajouterEmploye(employe, role.getText());
                        System.out.println(employe.getNom() + " " + employe.getPrenom() + " a été affecté.");
                        afficherEmployes(employe.getListe_employe());
                    }



                });

                // Ajouter le label et le bouton dans la HBox
                hbox.getChildren().addAll(labelEmploye, affectationButton);

                // Ajouter la HBox à la ListView
                listViewEmployes.getItems().add(hbox);
            }
        }
    }

}
