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
import java.util.List;

public class EmployeController {

    private  static Stage stage;  // Déclarer un champ Stage

    @FXML
    private Label information;


    // Setter pour passer le Stage
    public void setStage(Stage stage) {
        this.stage = stage;
        System.out.println("Le stage est initialisé dans controlle:  "+this.stage);
    }


    @FXML
    private ListView<String> listViewEmployes;  // Liste des employés dans l'interface
    @FXML
    private ListView<HBox> listViewEmployesBox;

    public void updateEmployeeList() {


        initialize();
    }

    // Méthode pour peupler la ListView avec la liste des employés
    public void populateEmployeeList() {
        listViewEmployesBox.getItems().clear();
        // Récupérer la liste d'employés de la classe Employe (liste statique)
        for (Employe employe : Employe.liste_employe) {
            // Créer un HBox pour chaque employé
            HBox hbox = new HBox();

            // Créer un label pour afficher le nom et le prénom
            Label label = new Label(employe.getNom() + " " + employe.getPrenom());

            // Créer un bouton pour l'employé
            Button button = new Button("Information");

            Button supresion=new Button("suprimer");

            Button historique= new Button("historique");

            // Associer une action au bouton
            button.setOnAction((ActionEvent e) -> {

                //System.out.println(employe.getNom());
                information.setText("Nom: "+employe.getNom()+" Prenom : "+employe.getPrenom()

                +" identifiant: "+ employe.getIdentifiant()+" âge: "+employe.getAge()
                        +" Rôle: "+employe.getType()+


                        " date d'embauche "+employe.getDate_embauche());

                // Action à faire lorsque le bouton est cliqué
                //afficherDetailsEmploye(employe);
            });
            // Associer une action au bouton
            supresion.setOnAction((ActionEvent e) -> {

                employe.suprimer_employe(employe.getIdentifiant());
                populateEmployeeList();
                // Action à faire lorsque le bouton est cliqué
                //afficherDetailsEmploye(employe);
            });
            historique.setOnAction((ActionEvent e) -> {

                String infoText = "Nom: " + employe.getNom() + " Prénom: " + employe.getPrenom() + "  Il a réalisé: ";

                // Vérifier si l'employé a des projets réalisés
                if (employe.getListe_projet_realise().isEmpty()) {
                    infoText += "Aucun projet réalisé.";
                } else {
                    // Ajouter chaque projet à la chaîne
                    for (String projet : employe.getListe_projet_realise()) {
                        infoText +=  projet;  // Assurez-vous d'avoir une méthode getNom() dans la classe Projet
                    }

                }

                // Afficher l'information complète dans le Label
                information.setText(infoText);
                System.out.println(employe.getListe_projet_realise());
                // Action à faire lorsque le bouton est cliqué
                //afficherDetailsEmploye(employe);
            });

            // Ajouter le label et le bouton à l'HBox
            hbox.getChildren().addAll(label, button,supresion,historique);

            // Ajouter l'HBox à la ListView
            //listViewEmployes.getItems().add(String.valueOf(hbox));
            listViewEmployesBox.getItems().add(hbox);
            // Optionnel : Cacher la ListView d'origine (si vous ne voulez pas qu'elle soit visible)
            listViewEmployes.setVisible(false);  // Cela cache la ListView
        }
    }

    // Méthode pour initialiser l'affichage de la liste des employés
    @FXML
    public void initialize() {
        // Création d'une instance de Employe
        Employe employeManager = new Employe(0, "","",0,"", new ArrayList<>(), LocalDate.now());


        // Récupérer la liste des employés
        List<Employe> listeEmployes = employeManager.getListe_employe();

        // Ajouter les employés au ListView sous forme de chaînes
        for (Employe e : listeEmployes) {
            listViewEmployes.getItems().add(e.getNom() + " " + e.getPrenom());  // Ajouter chaque employé sous forme de chaîne (prénom et nom)
        }
        populateEmployeeList();
    }

    public EmployeController() {
    }



    public void TestButtonClick(ActionEvent actionEvent) {

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
    private void OpenNewPagecreationemploye() {




        //System.out.println(stage);

        if (stage != null) {

            stage.setTitle("Nouvelle page");
            System.out.println("Page ouverte.");


        } else {
            System.out.println("Le stage est null");

        }

        try {


            // Charger la nouvelle page (Page2.fxml)
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("employe_creation.fxml"));

            // Transmettez la référence du contrôleur de la première page à la deuxième page
            //createEmpController.setParentController(this);

            Scene scene = new Scene(loader.load(), 800, 600);

            EmployeCreationController createEmpController = loader.getController();


            System.out.println("Le controller fille est "+createEmpController);

            // Transmettez la référence du contrôleur de la première page à la deuxième page
            createEmpController.setParentController(this);







            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("le bouton est cliqué.");

    }
}
