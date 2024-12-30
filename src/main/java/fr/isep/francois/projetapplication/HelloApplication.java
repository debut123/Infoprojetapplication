package fr.isep.francois.projetapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HelloApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("controle.fxml"));




        // Récupérer l'instance du contrôleur
        //HelloController controller = fxmlLoader.getController();
        // Passer le Stage au contrôleur via un constructeur ou via setController
        HelloController controller = new HelloController(); // Si vous avez un constructeur dans le contrôleur
        Controle controle=new Controle();
        EmployeController employeController=new EmployeController();
        EmployeCreationController employeCreationController=new EmployeCreationController();
        ProjetController projetController=new ProjetController();
        ProjetGestionController projetGestionController=new ProjetGestionController();
        ProjetAffectattionEmployeTache projetAffectattionEmployeTache=new ProjetAffectattionEmployeTache();
        Projetajoutemploye projetajoutemploye=new Projetajoutemploye();
        ProjetCreationTacheController projetCreationTacheController=new ProjetCreationTacheController();







        if (controller != null) {
            controller.setStage(stage);
            controle.setStage(stage);// Passer le stage au contrôleur
            employeController.setStage(stage);
            employeCreationController.setStage(stage);
            projetController.setStage(stage);
            projetGestionController.setStage(stage);
            projetAffectattionEmployeTache.setStage(stage);
            projetajoutemploye.setStage(stage);
            projetCreationTacheController.setStage(stage);


            System.out.println("Contrôleur correctement initialisé.");
            if (stage!=null){ //System.out.println("Le stage n'est pas null après le controller ");
            }
        } else {
            System.out.println("Le contrôleur est nul. Vérifiez l'attribut fx:controller dans le fichier FXML.");
        }




        Employe employeManager = new Employe(0, "","",0,"", new ArrayList<>(), LocalDate.now());

        // Ajout de 10 employés avec des informations variées
        employeManager.ajouter_employe(1, "Dupont", "Jean", 30, "Développeur", new ArrayList<>(), LocalDate.of(2020, 1, 15));
        employeManager.ajouter_employe(2, "Durand", "George", 28, "Chef de projet", new ArrayList<>(), LocalDate.of(2021, 6, 10));
        employeManager.ajouter_employe(3, "Lemoine", "Alice", 35, "Analyste", new ArrayList<>(), LocalDate.of(2019, 4, 5));
        employeManager.ajouter_employe(4, "Martin", "Pierre", 40, "Designer", new ArrayList<>(), LocalDate.of(2018, 7, 20));
        employeManager.ajouter_employe(5, "Bernard", "Lucie", 25, "Développeur Junior", new ArrayList<>(), LocalDate.of(2022, 11, 12));
        employeManager.ajouter_employe(6, "Petit", "Marc", 38, "Architecte logiciel", new ArrayList<>(), LocalDate.of(2017, 3, 25));
        employeManager.ajouter_employe(7, "Moreau", "Sophie", 29, "Chargée de communication", new ArrayList<>(), LocalDate.of(2021, 9, 5));
        employeManager.ajouter_employe(8, "Girard", "Thomas", 33, "Chef de projet", new ArrayList<>(), LocalDate.of(2020, 5, 17));
        employeManager.ajouter_employe(9, "Roux", "Camille", 27, "Développeur Front-end", new ArrayList<>(), LocalDate.of(2022, 2, 10));
        employeManager.ajouter_employe(10, "Blanc", "David", 45, "Responsable RH", new ArrayList<>(), LocalDate.of(2015, 8, 30));

        for (Employe employe: employeManager.getListe_employe()){

            employe.ajouterProjet("Projet Alpha");
        }


        // Initialisation d'un projet principal
        Map<Employe, String> employes = new HashMap<>();
        Projet projetPrincipal = new Projet("Projet Principal", new ArrayList<>(), LocalDate.now(), employes, 50000);
        /*

        projetPrincipal.ajouterProjet(projetPrincipal);
// Création et ajout de 10 projets
        for (int i = 1; i <= 10; i++) {
            Map<Employe, String> employeMap = new HashMap<>();

            // Ajouter 10 employés par projet (nommés Dupont Jean, Dupont Pierre, etc.)
            for (int j = 1; j <= 10; j++) {
                String nom = "Dupont" + j;
                String prenom = "Jean";
                Employe employe = new Employe(j, nom, prenom, 30 + j, "Rôle " + j, new ArrayList<>(), LocalDate.now());
                employeMap.put(employe, "Rang" + j);
            }

            String nomProjet = "Projet " + i;
            int budget = 10000 * i;  // Budget croissant
            LocalDate dateLimite = LocalDate.now().plusMonths(i); // Date limite croissante

            // Création du projet
            Projet projet = new Projet(nomProjet, new ArrayList<>(), dateLimite, employeMap, budget);

            // Création des tâches et ajout d'employés aux tâches
            Tache tache1 = new Tache("Tâche 1 - Analyse", "Haute", "Analyse des besoins", "description", new ArrayList<>());
            tache1.attribuerTache(employeMap.keySet().iterator().next());  // Assigner le premier employé

            Tache tache2 = new Tache("Tâche 2 - Développement", "Moyenne", "Développement de la fonctionnalité", "description", new ArrayList<>());
            tache2.attribuerTache(employeMap.keySet().stream().skip(4).findFirst().orElse(null)); // Assigner le 5e employé

            // Ajouter les tâches au projet
            projet.ajouterTache(tache1);
            projet.ajouterTache(tache2);

            // Ajouter le projet à la liste des projets du projet principal
            projetPrincipal.ajouterProjet(projet);
        }

*/
        projetController.setListeProjets(projetPrincipal.getListeProjets());

        Scene scene = new Scene(fxmlLoader.load(), 400, 300);


        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {



        launch();




        // Modifier l'employé
        //employe1.modifier_employe(2, "Durand", "Marie", 29, "Chef de projet senior", projets, LocalDate.of(2021, 6, 10));

    }
}