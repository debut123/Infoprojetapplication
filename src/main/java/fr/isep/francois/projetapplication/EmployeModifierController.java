package fr.isep.francois.projetapplication;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class EmployeModifierController {

    private static Stage stage;
    private static Employe employe;

    public static void setStage(Stage stage) {
        EmployeModifierController.stage = stage;
    }

    public EmployeModifierController() {
    }

    private EmployeController parentController;
    @FXML
    private Button enregistrement;
    @FXML
    private TextField nomChamps;
    @FXML
    private TextField prenomChamps;
    @FXML
    private TextField ageChamps;
    @FXML
    private TextField typeChamps;
    @FXML
    private TextField dateEmbaucheChamps;

    public static void setEmploye(Employe employe) {
        EmployeModifierController.employe = employe;
    }

    @FXML                                                           // Action Ã  executer pour le bouton modifier
    private void employeModifier() {
        String nom = nomChamps.getText();
        String prenom = prenomChamps.getText();
        String age = ageChamps.getText();
        String type = typeChamps.getText();
        String dateEmbauche = dateEmbaucheChamps.getText();
        // conditions minimales de nom et prÃ©nom
        if (nom.isEmpty() || prenom.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Remplissez les champs obligatoires, Ã  savoir prÃ©nom et nom");
        } else {
            employe.setNom(nom);
            employe.setPrenom(prenom);
            employe.setAge(Integer.parseInt(age));
            employe.setType(type);
            employe.setDate_embauche(LocalDate.parse(dateEmbauche));

            showAlert(Alert.AlertType.INFORMATION, "Enregistrement",
                    "Les informations personnelles de " + employe.getPrenom() + ' '
                            + employe.getNom() + " ont Ã©tÃ© modifiÃ©es avec succÃ¨s.");

            OpenNewPageEmploye();
        }
    }


    @FXML                                                           // bouton annuler les modifications
    private void annulerModifications() {
        showAlert(Alert.AlertType.INFORMATION, "Annulation", "La tentative de modification des informations de l'employÃ© a Ã©tÃ© annulÃ©.");
        OpenNewPageEmploye();
    }

    // mÃ©thode pour les Alert
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // mÃ©thode pour rÃ©initialiser les champs
    private void resetInfosEmployes() {
        nomChamps.setText("");
        prenomChamps.setText("");
        ageChamps.setText("");
        typeChamps.setText("");
        dateEmbaucheChamps.setText("");
    }

    @FXML
    public void setParentController(EmployeController parentController) {
        this.parentController = parentController;                           // Setter pour la rÃ©fÃ©rence du contrÃ´leur parent
    }

    public void initialize() {
        nomChamps.setText(employe.getNom());
        prenomChamps.setText(employe.getPrenom());
        ageChamps.setText(String.valueOf(employe.getAge()));
        typeChamps.setText(employe.getType());
        dateEmbaucheChamps.setText(String.valueOf(employe.getDate_embauche()));
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



            stage.setTitle("Gestion des employÃ©s");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("le bouton est cliquÃ©.");

    }


}
